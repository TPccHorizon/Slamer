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
    private static final String BINARY = "608060405262015180600a5534801561001757600080fd5b50604051610af9380380610af98339818101604052606081101561003a57600080fd5b5080516020820151604090920151909190336001600160a01b03841614156100c357604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601f60248201527f546865205350206d757374206e6f742062652074686520637573746f6d657200604482015290519081900360640190fd5b60008054336001600160a01b0319918216178255600180549091166001600160a01b03861617905560048390556005805461ffff1916905560098290556040517fabd60e51a18b36604f00b5547417cf9051cc5c3a2e80e68206296e14be19f0bc9190a15050506109c0806101396000396000f3fe6080604052600436106100fe5760003560e01c8063650d993b11610095578063a574971011610064578063a57497101461028a578063c828371e1461029f578063cfe79bcd146102b4578063d0e30db0146102e4578063fdf53247146102ec576100fe565b8063650d993b1461023657806369357e291461024b57806398d5fdca14610260578063a3ceab1414610275576100fe565b80633955f57f116100d15780633955f57f146101995780633d813512146101ae578063439f5ac2146101de578063571959e014610205576100fe565b8063205ca3bf1461010357806322f3e2d41461013557806328fd5e831461015e57806338e71f5614610191575b600080fd5b34801561010f57600080fd5b506101336004803603604081101561012657600080fd5b5080359060200135610301565b005b34801561014157600080fd5b5061014a610398565b604080519115158252519081900360200190f35b34801561016a57600080fd5b5061014a6004803603602081101561018157600080fd5b50356001600160a01b03166103c9565b6101336103dd565b3480156101a557600080fd5b50610133610453565b3480156101ba57600080fd5b50610133600480360360408110156101d157600080fd5b508035906020013561045d565b3480156101ea57600080fd5b506101f36104da565b60408051918252519081900360200190f35b34801561021157600080fd5b5061021a6104e0565b604080516001600160a01b039092168252519081900360200190f35b34801561024257600080fd5b5061021a6104ef565b34801561025757600080fd5b5061014a6104fe565b34801561026c57600080fd5b506101f3610517565b34801561028157600080fd5b5061013361051d565b34801561029657600080fd5b506101f36105ba565b3480156102ab57600080fd5b506101f36105bf565b3480156102c057600080fd5b50610133600480360360408110156102d757600080fd5b50803590602001356105c5565b61014a6105ea565b3480156102f857600080fd5b5061013361067a565b6000828152600360209081526040808320548151638753367f60e01b81526004810186905291516001600160a01b03909116938493638753367f936024808201949293918390030190829087803b15801561035b57600080fd5b505af115801561036f573d6000803e3d6000fd5b505050506040513d602081101561038557600080fd5b50516103935761039361051d565b505050565b600554600090610100900460ff161580156103b557506006805414155b80156103c457506103c46104fe565b905090565b6001546001600160a01b0391821691161490565b600854600754428203910360008160648402816103f657fe5b049050600060646104056105ba565b83028161040e57fe5b6001546040519290910492506001600160a01b03169082156108fc029083906000818181858888f1935050505015801561044c573d6000803e3d6000fd5b5050505050565b6000194201600855565b6000546001600160a01b0316331461047457600080fd5b8181604051610482906106c1565b9182526020820152604080519182900301906000f0801580156104a9573d6000803e3d6000fd5b5060009283526003602052604090922080546001600160a01b0319166001600160a01b039093169290921790915550565b60085490565b6000546001600160a01b031690565b6001546001600160a01b031690565b600754600090421180156103c457505060085442111590565b60045490565b6005805461ff0019166101001790556105346104fe565b15610541576105416103dd565b6000546001600160a01b03166108fc6105586105ba565b6040518115909202916000818181858888f19350505050158015610580573d6000803e3d6000fd5b50600680556040805142815290517fa6e781cf7274c3bf220e0f49c77aa4e799b92af6165ad4522b01b1cb71e27a5c9181900360200190a1565b303190565b60075490565b6000546001600160a01b031633146105dc57600080fd5b8181604051610482906106ce565b60006105f5336103c9565b8015610604575060055460ff16155b61060d57600080fd5b600454341461061b57600080fd5b6005805460ff19166001179055426007819055600a54600954020160085560408051348152905133917fb6b557fe9235cea11cc85bda5db86c09593e7f7903fee774c8bde722ec52daae919081900360200190a2506005600655600190565b6000546001600160a01b0316331461069157600080fd5b60036006556040517f118aa24572308142d7f42b6445d61a5b3c19219c170a0b7d306d0f7e5a6573a390600090a1565b61016b806106dc83390190565b610145806108478339019056fe6080604052600560025534801561001557600080fd5b5060405161016b38038061016b8339818101604052604081101561003857600080fd5b508051602090910151600091825560019190915560035561010d8061005e6000396000f3fe6080604052348015600f57600080fd5b506004361060325760003560e01c80637e810e281460375780638753367f14604f575b600080fd5b603d607d565b60408051918252519081900360200190f35b606960048036036020811015606357600080fd5b50356083565b604080519115158252519081900360200190f35b60015490565b600060035482111560cf57600180548101908190556002541160cf576040517f7c54d659bf055650f48970c744c98932fcb42694a40d5a19cf1bd5f2076c233890600090a150600060d3565b5060015b91905056fea265627a7a72305820787042aae768b228016a0b1296dde552e1d30a394c61aab37fd138744cbe2ee964736f6c634300050900326080604052600560025534801561001557600080fd5b506040516101453803806101458339818101604052604081101561003857600080fd5b5080516020909101516000828155600181905560038290556040517fc092d3c5d4b54046e57c1483b350acac42388180f0f2048fe7cf544886e433479190a1505060be806100876000396000f3fe6080604052348015600f57600080fd5b506004361060325760003560e01c80637e810e281460375780638753367f14604f575b600080fd5b603d607d565b60408051918252519081900360200190f35b606960048036036020811015606357600080fd5b50356083565b604080519115158252519081900360200190f35b60015490565b5060019056fea265627a7a723058208d6ebc6aac499a6e068029cd03b1348ccf163e5a7bf2f158f6aabd175d878d3364736f6c63430005090032a265627a7a72305820abbe5e9f8104ef8306e769b58986c837be161d07a5cf038a0232842f3a785ce264736f6c63430005090032";

    public static final String FUNC_VERIFYAVERAGERESPONSETIME = "verifyAverageResponseTime";

    public static final String FUNC_ISACTIVE = "isActive";

    public static final String FUNC_ISCUSTOMER = "isCustomer";

    public static final String FUNC_COMPENSATECUSTOMER = "compensateCustomer";

    public static final String FUNC_EXPIRESLA = "expireSLA";

    public static final String FUNC_ADDAVRGRESPONSETIME = "addAvrgResponseTime";

    public static final String FUNC_GETENDTIME = "getEndTime";

    public static final String FUNC_GETSERVICEPROVIDER = "getServiceProvider";

    public static final String FUNC_GETCUSTOMER = "getCustomer";

    public static final String FUNC_ISSTILLINVALIDITY = "isStillInValidity";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_TERMINATESLA = "terminateSLA";

    public static final String FUNC_GETCURRENTBALANCE = "getCurrentBalance";

    public static final String FUNC_GETSTARTTIME = "getStartTime";

    public static final String FUNC_ADDUPTIME = "addUptime";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_CONFIRMCOMPLETE = "confirmComplete";

    public static final Event CUSTOMERDEPOSIT_EVENT = new Event("CustomerDeposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CONTRACTCREATED_EVENT = new Event("ContractCreated", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event CONTRACTCOMPLETE_EVENT = new Event("ContractComplete", 
            Arrays.<TypeReference<?>>asList());
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

    public RemoteCall<TransactionReceipt> verifyAverageResponseTime(BigInteger _sloId, BigInteger _measured) {
        final Function function = new Function(
                FUNC_VERIFYAVERAGERESPONSETIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sloId), 
                new org.web3j.abi.datatypes.generated.Uint256(_measured)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<TransactionReceipt> expireSLA() {
        final Function function = new Function(
                FUNC_EXPIRESLA, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addAvrgResponseTime(BigInteger _id, BigInteger _responseTime) {
        final Function function = new Function(
                FUNC_ADDAVRGRESPONSETIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_responseTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<TransactionReceipt> addUptime(BigInteger _id, BigInteger _availability) {
        final Function function = new Function(
                FUNC_ADDUPTIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_availability)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> deposit(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> confirmComplete() {
        final Function function = new Function(
                FUNC_CONFIRMCOMPLETE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public List<ContractCreatedEventResponse> getContractCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CONTRACTCREATED_EVENT, transactionReceipt);
        ArrayList<ContractCreatedEventResponse> responses = new ArrayList<ContractCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ContractCreatedEventResponse typedResponse = new ContractCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ContractCreatedEventResponse> contractCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ContractCreatedEventResponse>() {
            @Override
            public ContractCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CONTRACTCREATED_EVENT, log);
                ContractCreatedEventResponse typedResponse = new ContractCreatedEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<ContractCreatedEventResponse> contractCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONTRACTCREATED_EVENT));
        return contractCreatedEventFlowable(filter);
    }

    public List<ContractCompleteEventResponse> getContractCompleteEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CONTRACTCOMPLETE_EVENT, transactionReceipt);
        ArrayList<ContractCompleteEventResponse> responses = new ArrayList<ContractCompleteEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ContractCompleteEventResponse typedResponse = new ContractCompleteEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ContractCompleteEventResponse> contractCompleteEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ContractCompleteEventResponse>() {
            @Override
            public ContractCompleteEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CONTRACTCOMPLETE_EVENT, log);
                ContractCompleteEventResponse typedResponse = new ContractCompleteEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<ContractCompleteEventResponse> contractCompleteEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONTRACTCOMPLETE_EVENT));
        return contractCompleteEventFlowable(filter);
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

    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _customer, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _customer, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _customer, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _customer, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class CustomerDepositEventResponse {
        public Log log;

        public String from;

        public BigInteger value;
    }

    public static class ContractCreatedEventResponse {
        public Log log;
    }

    public static class ContractCompleteEventResponse {
        public Log log;
    }

    public static class SLATerminatedEventResponse {
        public Log log;

        public BigInteger timeOfTermination;
    }
}
