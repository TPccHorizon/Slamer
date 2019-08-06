package ch.uzh.slamer.backend.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class Uptime extends Contract {
    private static final String BINARY = "6080604052600560025534801561001557600080fd5b506040516101453803806101458339818101604052604081101561003857600080fd5b5080516020909101516000828155600181905560038290556040517fc092d3c5d4b54046e57c1483b350acac42388180f0f2048fe7cf544886e433479190a1505060be806100876000396000f3fe6080604052348015600f57600080fd5b506004361060325760003560e01c80637e810e281460375780638753367f14604f575b600080fd5b603d607d565b60408051918252519081900360200190f35b606960048036036020811015606357600080fd5b50356083565b604080519115158252519081900360200190f35b60015490565b5060019056fea265627a7a723058208d6ebc6aac499a6e068029cd03b1348ccf163e5a7bf2f158f6aabd175d878d3364736f6c63430005090032";

    public static final String FUNC_GETVIOLATIONS = "getViolations";

    public static final String FUNC_VERIFY = "verify";

    public static final Event SLOADDED_EVENT = new Event("SloAdded", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event VIOLATED_EVENT = new Event("Violated", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected Uptime(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Uptime(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Uptime(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Uptime(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getViolations() {
        final Function function = new Function(FUNC_GETVIOLATIONS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> verify(BigInteger _measured) {
        final Function function = new Function(
                FUNC_VERIFY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_measured)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<SloAddedEventResponse> getSloAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SLOADDED_EVENT, transactionReceipt);
        ArrayList<SloAddedEventResponse> responses = new ArrayList<SloAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SloAddedEventResponse typedResponse = new SloAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SloAddedEventResponse> sloAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, SloAddedEventResponse>() {
            @Override
            public SloAddedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SLOADDED_EVENT, log);
                SloAddedEventResponse typedResponse = new SloAddedEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<SloAddedEventResponse> sloAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SLOADDED_EVENT));
        return sloAddedEventFlowable(filter);
    }

    public List<ViolatedEventResponse> getViolatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VIOLATED_EVENT, transactionReceipt);
        ArrayList<ViolatedEventResponse> responses = new ArrayList<ViolatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ViolatedEventResponse typedResponse = new ViolatedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ViolatedEventResponse> violatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ViolatedEventResponse>() {
            @Override
            public ViolatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VIOLATED_EVENT, log);
                ViolatedEventResponse typedResponse = new ViolatedEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<ViolatedEventResponse> violatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VIOLATED_EVENT));
        return violatedEventFlowable(filter);
    }

    @Deprecated
    public static Uptime load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Uptime(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Uptime load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Uptime(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Uptime load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Uptime(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Uptime load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Uptime(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Uptime> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _id, BigInteger _availability) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_availability)));
        return deployRemoteCall(Uptime.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Uptime> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _id, BigInteger _availability) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_availability)));
        return deployRemoteCall(Uptime.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Uptime> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _id, BigInteger _availability) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_availability)));
        return deployRemoteCall(Uptime.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Uptime> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _id, BigInteger _availability) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_availability)));
        return deployRemoteCall(Uptime.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class SloAddedEventResponse {
        public Log log;
    }

    public static class ViolatedEventResponse {
        public Log log;
    }
}
