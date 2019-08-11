package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.MeasuredResponseTime;
import ch.uzh.slamer.backend.model.enums.LifecyclePhase;
import ch.uzh.slamer.backend.model.enums.SlaStatus;
import ch.uzh.slamer.backend.model.pojo.SlaForMonitoring;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import ch.uzh.slamer.backend.smartcontract.BlockchainConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.request.EthFilter;

@Service
public class MonitoringService {

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    SlaUserRepository slaUserRepository;

    public boolean checkResponseTime(MeasuredResponseTime measured) {
        SlaForMonitoring sla = getSLA(measured.getSlaId());
//        if (!isCorrectWallet(sla, measured)) {
//            return false;
//        }
        System.out.println("Validate SLO");
        BlockchainConnector connector = getBlockchainConnector(sla);
        EthFilter filter = connector.getGlobalFilter(sla.getSla().getHash());
        connector.getWeb3j().ethLogFlowable(filter).subscribe(log -> {
            System.out.println("SLA is terminated by violation");
            System.out.println(log.toString());
            sla.getSla().setStatus(SlaStatus.INACTIVE.getStatus());
            sla.getSla().setLifecyclePhase(LifecyclePhase.TERMINATION.getPhase());
            slaRepository.update(sla.getSla());
        });
        try {
            connector.validateResponseTime(measured, sla);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private SlaForMonitoring getSLA(int slaId) {
        SlaForMonitoring sla = null;
        try {
            sla = slaRepository.getSlaForMonitoring(slaId);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("SLA not found with id " + slaId);
        }
        return sla;
    }

    private BlockchainConnector getBlockchainConnector(SlaForMonitoring sla){
        /* Initialize contract manager with monitoring solution account */
        return new BlockchainConnector(sla.getMonitoringSolution().getPrivateKey());
    }

    private boolean isCorrectWallet(SlaForMonitoring sla, MeasuredResponseTime measured) {
        return sla.getMonitoringSolution().getWallet().equals(measured.getWallet());
    }
}
