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
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
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
public class SimpleSLA extends Contract {
    private static final String BINARY = "60806040526201518060095534801561001757600080fd5b506040516105153803806105158339818101604052606081101561003a57600080fd5b5080516020820151604090920151909190336001600160a01b038316141561006157600080fd5b6003928355600080546001600160a01b03199081163317909155600180546001600160a01b039490941693909116929092179091556004805461ffff1916905560059190915560085561045c806100b96000396000f3fe6080604052600436106100a75760003560e01c806369357e291161006457806369357e291461017f57806398d5fdca14610194578063a3ceab14146101a9578063a5749710146101be578063c828371e146101d3578063d0e30db0146101e8576100a7565b806322f3e2d4146100ac57806328fd5e83146100d557806338e71f5614610108578063439f5ac214610112578063571959e014610139578063650d993b1461016a575b600080fd5b3480156100b857600080fd5b506100c16101f0565b604080519115158252519081900360200190f35b3480156100e157600080fd5b506100c1600480360360208110156100f857600080fd5b50356001600160a01b0316610222565b610110610236565b005b34801561011e57600080fd5b506101276102ac565b60408051918252519081900360200190f35b34801561014557600080fd5b5061014e6102b2565b604080516001600160a01b039092168252519081900360200190f35b34801561017657600080fd5b5061014e6102c1565b34801561018b57600080fd5b506100c16102d0565b3480156101a057600080fd5b506101276102e9565b3480156101b557600080fd5b506101106102ef565b3480156101ca57600080fd5b5061012761038d565b3480156101df57600080fd5b50610127610392565b6100c1610398565b600454600090610100900460ff1615801561020e5750600554600614155b801561021d575061021d6102d0565b905090565b6001546001600160a01b0391821691161490565b6007546006544282039103600081606484028161024f57fe5b0490506000606461025e61038d565b83028161026757fe5b6001546040519290910492506001600160a01b03169082156108fc029083906000818181858888f193505050501580156102a5573d6000803e3d6000fd5b5050505050565b60075490565b6000546001600160a01b031690565b6001546001600160a01b031690565b6006546000904211801561021d57505060075442111590565b60035490565b6004805461ff0019166101001790556103066102d0565b1561031357610313610236565b6000546001600160a01b03166108fc61032a61038d565b6040518115909202916000818181858888f19350505050158015610352573d6000803e3d6000fd5b5060066005556040805142815290517fa6e781cf7274c3bf220e0f49c77aa4e799b92af6165ad4522b01b1cb71e27a5c9181900360200190a1565b303190565b60065490565b60006103a333610222565b80156103b2575060045460ff16155b6103bb57600080fd5b60035434146103c957600080fd5b6004805460ff19166001179055426006819055600954600854020160075560408051348152905133917fb6b557fe9235cea11cc85bda5db86c09593e7f7903fee774c8bde722ec52daae919081900360200190a2506005805560019056fea265627a7a723058208cc6d36bb3fa487f606e3a63843e684a1cdaff9a09e1e69a5bc9fc8e8259a90a64736f6c63430005090032";

    public static final String FUNC_ISACTIVE = "isActive";

    public static final String FUNC_ISCUSTOMER = "isCustomer";

    public static final String FUNC_COMPENSATECUSTOMER = "compensateCustomer";

    public static final String FUNC_GETENDTIME = "getEndTime";

    public static final String FUNC_GETSERVICEPROVIDER = "getServiceProvider";

    public static final String FUNC_GETCUSTOMER = "getCustomer";

    public static final String FUNC_ISSTILLINVALIDITY = "isStillInValidity";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_TERMINATESLA = "terminateSLAByViolation";

    public static final String FUNC_GETCURRENTBALANCE = "getCurrentBalance";

    public static final String FUNC_GETSTARTTIME = "getStartTime";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final Event CUSTOMERDEPOSIT_EVENT = new Event("CustomerDeposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SLATERMINATED_EVENT = new Event("SLATerminated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected SimpleSLA(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleSLA(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SimpleSLA(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SimpleSLA(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Boolean> isActive() {
        final Function function = new Function(FUNC_ISACTIVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> isCustomer(String _addr) {
        final Function function = new Function(FUNC_ISCUSTOMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> compensateCustomer(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_COMPENSATECUSTOMER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> getEndTime() {
        final Function function = new Function(FUNC_GETENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getServiceProvider() {
        final Function function = new Function(FUNC_GETSERVICEPROVIDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getCustomer() {
        final Function function = new Function(FUNC_GETCUSTOMER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isStillInValidity() {
        final Function function = new Function(FUNC_ISSTILLINVALIDITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> getPrice() {
        final Function function = new Function(FUNC_GETPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> terminateSLA() {
        final Function function = new Function(
                FUNC_TERMINATESLA, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getCurrentBalance() {
        final Function function = new Function(FUNC_GETCURRENTBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getStartTime() {
        final Function function = new Function(FUNC_GETSTARTTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> deposit(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public List<CustomerDepositEventResponse> getCustomerDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CUSTOMERDEPOSIT_EVENT, transactionReceipt);
        ArrayList<CustomerDepositEventResponse> responses = new ArrayList<CustomerDepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CustomerDepositEventResponse typedResponse = new CustomerDepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CustomerDepositEventResponse> customerDepositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, CustomerDepositEventResponse>() {
            @Override
            public CustomerDepositEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CUSTOMERDEPOSIT_EVENT, log);
                CustomerDepositEventResponse typedResponse = new CustomerDepositEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CustomerDepositEventResponse> customerDepositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CUSTOMERDEPOSIT_EVENT));
        return customerDepositEventFlowable(filter);
    }

    public List<SLATerminatedEventResponse> getSLATerminatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SLATERMINATED_EVENT, transactionReceipt);
        ArrayList<SLATerminatedEventResponse> responses = new ArrayList<SLATerminatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SLATerminatedEventResponse typedResponse = new SLATerminatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.timeOfTermination = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SLATerminatedEventResponse> sLATerminatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, SLATerminatedEventResponse>() {
            @Override
            public SLATerminatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SLATERMINATED_EVENT, log);
                SLATerminatedEventResponse typedResponse = new SLATerminatedEventResponse();
                typedResponse.log = log;
                typedResponse.timeOfTermination = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SLATerminatedEventResponse> sLATerminatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SLATERMINATED_EVENT));
        return sLATerminatedEventFlowable(filter);
    }

    @Deprecated
    public static SimpleSLA load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleSLA(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SimpleSLA load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleSLA(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SimpleSLA load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SimpleSLA(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SimpleSLA load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SimpleSLA(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _price, String _customer, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _price, String _customer, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _price, String _customer, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _price, String _customer, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class CustomerDepositEventResponse {
        public Log log;

        public String from;

        public BigInteger value;
    }

    public static class SLATerminatedEventResponse {
        public Log log;

        public BigInteger timeOfTermination;
    }
}
