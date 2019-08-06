package ch.uzh.slamer.backend.smartcontract;

import ch.uzh.slamer.backend.contracts.SimpleSLA;
import ch.uzh.slamer.backend.model.dto.MeasuredResponseTime;
import ch.uzh.slamer.backend.model.pojo.SlaForMonitoring;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SLAContractManager {

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    private final static BigInteger WEI = BigInteger.valueOf(1000000000000000000L);

    private Web3j web3j;
    private TransactionManager transactionManager;
    private ContractGasProvider contractGasProvider;

    public SLAContractManager(String privateKey) {
        connect(privateKey);
    }

    private void connect(String privateKey) {
        this.web3j = Web3j.build(new HttpService());
        this.transactionManager = new RawTransactionManager(
                web3j,
                getCredentialsFromKey(privateKey)
        );
        this.contractGasProvider = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);
    }

    public String deployContract(Sla sla, SlaUser customer) throws Exception {
        long diff = sla.getValidTo().getTime() - sla.getValidFrom().getTime();
        BigInteger daysOfValidity = BigInteger.valueOf(diff/ (1000 * 60 * 60 * 24));
        System.out.println("DAYS: " + daysOfValidity.toString());
        BigInteger price = etherToWei(sla.getServicePrice().floatValue());
        System.out.println("Account/Wallet: " + customer.getWallet());
        System.out.println("price" + price.toString());
        return SimpleSLA.deploy(web3j,
                                transactionManager,
                                contractGasProvider,
                                customer.getWallet(),
                                price,
                                daysOfValidity)
                .send()
                .getContractAddress();
    }

    public void depositFunds(String contractAddress, int depositValue) throws Exception {
        SimpleSLA sla = SimpleSLA.load(contractAddress, web3j, transactionManager, contractGasProvider);
        BigInteger weiValue = etherToWei((float) depositValue);
        System.out.println("Wei Value: " + weiValue.toString());
        sla.deposit(weiValue).send();
    }

    public TransactionReceipt terminateSLA(String contractAddress) throws Exception {
        SimpleSLA sla = SimpleSLA.load(contractAddress, web3j, transactionManager, contractGasProvider);
        return sla.terminateSLA().send();
    }

    public void validateResponseTime(MeasuredResponseTime measured, SlaForMonitoring sla) {
        SimpleSLA contract = SimpleSLA.load(sla.getSla().getHash(), web3j, transactionManager, contractGasProvider);
        int multipliedValue = (int) measured.getMeasured() * 100;
        contract.verifyAverageResponseTime(BigInteger.valueOf(measured.getSloId()), BigInteger.valueOf(multipliedValue));
    }

    public String getSLAData(String contractAddress) throws Exception {
        SimpleSLA sla = SimpleSLA.load(contractAddress, web3j, transactionManager, contractGasProvider);
        String serviceProvider = sla.getServiceProvider().send();
        String customer = sla.getCustomer().send();
        BigInteger price = sla.getPrice().send();
        return "SP Address: " + serviceProvider + " , Customer Address: " + customer + " , Price: " + price.toString();
    }

    public String getWalletBalance(String walletAddress) {
        String balance;
        try {
            EthGetBalance weiBalance = web3j.ethGetBalance(walletAddress, DefaultBlockParameterName.LATEST).send();
            balance = weiToEther(weiBalance.getBalance()).toString();
            return balance;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not get Wallet balance");
            return null;
        }
    }

    public boolean isSLAValid(String contractAddress) throws Exception {
        SimpleSLA sla = SimpleSLA.load(contractAddress, web3j, transactionManager, contractGasProvider);
        return sla.isStillInValidity().send();
    }

    public float getEtherContractBalance(String contractAddress) throws Exception {
        SimpleSLA sla = SimpleSLA.load(contractAddress, web3j, transactionManager, contractGasProvider);
        return sla.getCurrentBalance().send().floatValue() / WEI.floatValue();
    }

    private Credentials getCredentialsFromKey(String privateKey) {
        return Credentials.create(privateKey);
    }

    private Credentials getCredentials(String privateKey, String publicKey) {
        return Credentials.create(privateKey, publicKey);
    }

    public SimpleSLA loadContract(String address) {
        return SimpleSLA.load(address, web3j, transactionManager, contractGasProvider);
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    private BigInteger etherToWei(float ether) {
        System.out.println("Float ether: " + ether);
        System.out.println("WEI: " + WEI.toString());
        System.out.println("result: " + WEI.multiply(BigInteger.valueOf((long) ether)));
        return WEI.multiply(BigInteger.valueOf((long) ether));
    }

    private BigDecimal weiToEther(BigInteger wei) {
        BigDecimal decimalWei = new BigDecimal(wei).setScale(2, RoundingMode.CEILING);
        BigDecimal etherWeiValue = new BigDecimal(WEI).setScale(2, RoundingMode.CEILING);
        return decimalWei.divide(etherWeiValue).setScale(2, RoundingMode.CEILING);
    }

}
