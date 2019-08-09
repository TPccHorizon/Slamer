package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.contracts.SimpleSLA;
import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.enums.LifecyclePhase;
import ch.uzh.slamer.backend.model.enums.SlaStatus;
import ch.uzh.slamer.backend.model.pojo.SmartContractDeployment;
import ch.uzh.slamer.backend.model.pojo.SmartContractDeposit;
import ch.uzh.slamer.backend.repository.ServiceLevelObjectiveRepository;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import ch.uzh.slamer.backend.smartcontract.SLAContractManager;
import codegen.tables.pojos.ServiceLevelObjective;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import io.reactivex.disposables.Disposable;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;


@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
public class SmartContractController {

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    SlaUserRepository slaUserRepository;

    @Autowired
    ServiceLevelObjectiveRepository sloRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/deploy")
    public ResponseEntity<Boolean> deploySLA(@RequestBody SmartContractDeployment deployment) {
        String contractAddress;
        Sla sla;
        Disposable subscription = null;
        try {
            sla = slaRepository.findById(deployment.getSlaId());
            SlaUser customer = slaUserRepository.findById(sla.getServiceCustomerId());
            SlaUser provider = slaUserRepository.findById(sla.getServiceProviderId());
            SLAContractManager contractManager = new SLAContractManager(provider.getPrivateKey());
            contractAddress = contractManager.deployContract(sla, customer);
            List<ServiceLevelObjective> slos = sloRepository.getAllBySlaId(sla.getId());
            System.out.println("Deployed Smart Contract");
            contractManager.addSLOsOnContractCreation(slos, contractAddress);
        }
        catch (RecordNotFoundException e) {
            e.printStackTrace();
            if (subscription != null) {
                subscription.dispose();
            }
            System.out.println("SLA not found with ID " + deployment.getSlaId());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
            if (subscription != null) {
                subscription.dispose();
            }
            System.out.println("Failed to deploy Smart Contract");
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }

        if (deployment.getServiceProviderId() != sla.getServiceProviderId()) {
            System.out.println("The SLA can only be deployed by the Service Provider");
            subscription.dispose();
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        sla.setStatus(SlaStatus.DEPLOYMENT.getStatus());
        sla.setHash(contractAddress);
        slaRepository.update(sla);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/deposit")
    public ResponseEntity<Boolean> activateSLA(@RequestBody SmartContractDeposit deposit) {
        SlaUser customer;
        try {
            customer = slaUserRepository.findById(deposit.getCustomerId());
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("Customer not found with ID " + deposit.getCustomerId());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        String contractAddress;
        Sla sla;
        try {
            sla = slaRepository.findById(deposit.getSlaId());
            contractAddress = sla.getHash();
            if (contractAddress == null || contractAddress.equals("") || !sla.getStatus().equals(SlaStatus.DEPLOYMENT.getStatus())) {
                System.out.println("SLA is not active or has no Smart Contract Address");
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }
            SLAContractManager contractManager = new SLAContractManager(customer.getPrivateKey());
            contractManager.listenForEvents(contractAddress);
            EthFilter eventFilter = contractManager.getGlobalFilter(contractAddress);
            contractManager.getWeb3j().ethLogFlowable(eventFilter).subscribe(log -> {
                // update SLA status
                sla.setStatus(SlaStatus.ACTIVE.getStatus());
                sla.setLifecyclePhase(LifecyclePhase.MONITORING.getPhase());
                slaRepository.update(sla);
            });
            contractManager.depositFunds(contractAddress, deposit.getSlaPrice());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("SLA not found with ID " + deposit.getSlaId());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Contract could not be activated");
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/balance/{id}/{userId}")
    public ResponseEntity<Float> getContractBalance(@PathVariable int id, @PathVariable int userId) {
        Sla sla;
        SlaUser slaUser;
        try {
            sla = slaRepository.findById(id);
            slaUser = slaUserRepository.findById(userId);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("SLA not found with ID " + id);
            return new ResponseEntity<>(Float.intBitsToFloat(0), HttpStatus.NOT_FOUND);
        }

        if (!sla.getStatus().equals(SlaStatus.ACTIVE.getStatus())) {
            System.out.println("SLA is not active and therefore has no balance");
            return new ResponseEntity<>(Float.intBitsToFloat(0), HttpStatus.NOT_FOUND);
        }
        if (!sla.getServiceProviderId().equals(slaUser.getId()) || !sla.getServiceCustomerId().equals(slaUser.getId())) {
            System.out.println("Accessing User is not part of the SLA");
            return new ResponseEntity<>(Float.intBitsToFloat(0), HttpStatus.UNAUTHORIZED);
        }
        SLAContractManager contractManager = new SLAContractManager(slaUser.getPrivateKey());
        try {
            float balance = contractManager.getEtherContractBalance(sla.getHash());
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Float.intBitsToFloat(0), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/validity/{slaId}/{userId}")
    public ResponseEntity<Boolean> isValid(@PathVariable int slaId, @PathVariable int userId) {
        Sla sla;
        SlaUser slaUser;
        try {
            sla = slaRepository.findById(slaId);
            slaUser = slaUserRepository.findById(userId);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("SLA or Party not found ");
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        if (!sla.getServiceCustomerId().equals(slaUser.getId()) || !sla.getServiceProviderId().equals(slaUser.getId())) {
            System.out.println("Accessing Party is not part of the SLA");
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        SLAContractManager contractManager = new SLAContractManager(slaUser.getPrivateKey());
        try {
            boolean isValid = contractManager.isSLAValid(sla.getHash());
            return new ResponseEntity<>(isValid, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/info/{id}")
    public ResponseEntity<String> getInfo(@PathVariable int id) {
        Sla sla;
        SlaUser user;
        try {
            sla = slaRepository.findById(id);
            user = slaUserRepository.findById(sla.getServiceProviderId());
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("SLA Not found", HttpStatus.NOT_FOUND);
        }
        SLAContractManager manager = new SLAContractManager(user.getPrivateKey());
        try {
            String data = manager.getSLAData(sla.getHash());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("NO Data for SLA", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/terminate/{id}")
    public ResponseEntity<String> terminateSLAByViolation(@PathVariable int id) {
        Sla sla;
        SlaUser user;
        try {
            sla = slaRepository.findById(id);
            user = slaUserRepository.findById(sla.getServiceCustomerId());
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("SLA or User not found");
            return new ResponseEntity<>("SLA or User not found", HttpStatus.NOT_FOUND);
        }
        SLAContractManager manager = new SLAContractManager(user.getPrivateKey());
        try {
            TransactionReceipt receipt = manager.terminateSLA(sla.getHash());
            sla.setLifecyclePhase(LifecyclePhase.TERMINATION.getPhase());
            sla.setStatus(SlaStatus.INACTIVE.getStatus());
            slaRepository.update(sla);
            String txReceipt = "TxHash: " + receipt.getTransactionHash() + ", Status: " + receipt.getStatus() + ", Logs: ";
            for (Log log : receipt.getLogs()) {
                txReceipt = txReceipt + log.toString();
            }
            return new ResponseEntity<>(txReceipt, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not perform termination");
            return new ResponseEntity<>("Could not perform termination", HttpStatus.CONFLICT);
        }
    }

//    @RequestMapping(method = RequestMethod.DELETE, path = "/expire/{id}")
//    public ResponseEntity<String> terminateNormally(@PathVariable int id) {
//        Sla sla;
//        SlaUser user;
//        try {
//            sla = slaRepository.findById(id);
//            user = slaUserRepository.findById(sla.getServiceCustomerId());
//        } catch (RecordNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("SLA or User not found");
//            return new ResponseEntity<>("SLA or User not found", HttpStatus.NOT_FOUND);
//        }
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/balance/{userId}")
    public ResponseEntity<String> getBalance(@PathVariable int userId) {
        SlaUser user;
        try {
            user = slaUserRepository.findById(userId);
            SLAContractManager manager = new SLAContractManager(user.getPrivateKey());
            String balance = manager.getWalletBalance(user.getWallet());
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find User");
            return new ResponseEntity<>("Could not find User", HttpStatus.NOT_FOUND);
        }
    }
}
