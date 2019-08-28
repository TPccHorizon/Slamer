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
    private static final String BINARY = "608060405262015180600a5534801561001757600080fd5b50604051610fae380380610fae8339818101604052608081101561003a57600080fd5b5080516020820151604083015160609093015191929091336001600160a01b03851614156100c957604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601f60248201527f546865205350206d757374206e6f742062652074686520637573746f6d657200604482015290519081900360640190fd5b336001600160a01b038416141561012b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526029815260200180610f856029913960400191505060405180910390fd5b60008054336001600160a01b03199182161782556001805482166001600160a01b03888116919091179091556002805490921690861617905560048390556005805461ffff1916905560098290556040517fabd60e51a18b36604f00b5547417cf9051cc5c3a2e80e68206296e14be19f0bc9190a150505050610dd2806101b36000396000f3fe6080604052600436106101145760003560e01c8063650d993b116100a0578063c826fac011610064578063c826fac0146102df578063c828371e146102f4578063cfe79bcd14610309578063d0e30db014610339578063fdf532471461034157610114565b8063650d993b1461027657806369357e291461028b57806398d5fdca146102a0578063a3ceab14146102b5578063a5749710146102ca57610114565b806338e71f56116100e757806338e71f56146101d15780633955f57f146101d95780633d813512146101ee578063439f5ac21461021e578063571959e01461024557610114565b8063136e364514610119578063205ca3bf1461015757806322f3e2d41461018957806328fd5e831461019e575b600080fd5b34801561012557600080fd5b506101436004803603602081101561013c57600080fd5b5035610356565b604080519115158252519081900360200190f35b34801561016357600080fd5b506101876004803603604081101561017a57600080fd5b50803590602001356103d7565b005b34801561019557600080fd5b506101436104bf565b3480156101aa57600080fd5b50610143600480360360208110156101c157600080fd5b50356001600160a01b03166104f0565b610187610504565b3480156101e557600080fd5b5061018761057a565b3480156101fa57600080fd5b506101876004803603604081101561021157600080fd5b5080359060200135610584565b34801561022a57600080fd5b50610233610633565b60408051918252519081900360200190f35b34801561025157600080fd5b5061025a610639565b604080516001600160a01b039092168252519081900360200190f35b34801561028257600080fd5b5061025a610648565b34801561029757600080fd5b50610143610657565b3480156102ac57600080fd5b50610233610670565b3480156102c157600080fd5b50610187610676565b3480156102d657600080fd5b50610233610713565b3480156102eb57600080fd5b5061025a610718565b34801561030057600080fd5b50610233610727565b34801561031557600080fd5b506101876004803603604081101561032c57600080fd5b508035906020013561072d565b610143610784565b34801561034d57600080fd5b506101876108f4565b6000818152600360209081526040808320548151635d1ca63160e01b815291516001600160a01b0390911692635d1ca6319260048082019391829003018186803b1580156103a357600080fd5b505afa1580156103b7573d6000803e3d6000fd5b505050506040513d60208110156103cd57600080fd5b5051151592915050565b6002546001600160a01b031633146104205760405162461bcd60e51b8152600401808060200182810382526032815260200180610cf16032913960400191505060405180910390fd5b61042861096d565b6000828152600360209081526040808320548151638753367f60e01b81526004810186905291516001600160a01b03909116938493638753367f936024808201949293918390030190829087803b15801561048257600080fd5b505af1158015610496573d6000803e3d6000fd5b505050506040513d60208110156104ac57600080fd5b50516104ba576104ba610676565b505050565b600554600090610100900460ff161580156104dc57506006805414155b80156104eb57506104eb610657565b905090565b6001546001600160a01b0391821691161490565b6008546007544282039103600081606484028161051d57fe5b0490506000606461052c610713565b83028161053557fe5b6001546040519290910492506001600160a01b03169082156108fc029083906000818181858888f19350505050158015610573573d6000803e3d6000fd5b5050505050565b6000194201600855565b6000546001600160a01b031633146105cd5760405162461bcd60e51b8152600401808060200182810382526030815260200180610d466030913960400191505060405180910390fd5b81816040516105db90610989565b9182526020820152604080519182900301906000f080158015610602573d6000803e3d6000fd5b5060009283526003602052604090922080546001600160a01b0319166001600160a01b039093169290921790915550565b60085490565b6000546001600160a01b031690565b6001546001600160a01b031690565b600754600090421180156104eb57505060085442111590565b60045490565b6005805461ff00191661010017905561068d610657565b1561069a5761069a610504565b6000546001600160a01b03166108fc6106b1610713565b6040518115909202916000818181858888f193505050501580156106d9573d6000803e3d6000fd5b50600680556040805142815290517fa6e781cf7274c3bf220e0f49c77aa4e799b92af6165ad4522b01b1cb71e27a5c9181900360200190a1565b303190565b6002546001600160a01b031690565b60075490565b6000546001600160a01b031633146107765760405162461bcd60e51b8152600401808060200182810382526030815260200180610d466030913960400191505060405180910390fd5b81816040516105db90610996565b6001546000906001600160a01b031633146107d05760405162461bcd60e51b8152600401808060200182810382526028815260200180610d766028913960400191505060405180910390fd5b6107d86104bf565b156108145760405162461bcd60e51b8152600401808060200182810382526023815260200180610d236023913960400191505060405180910390fd5b60045434146108545760405162461bcd60e51b8152600401808060200182810382526022815260200180610caa6022913960400191505060405180910390fd5b6006546003146108955760405162461bcd60e51b8152600401808060200182810382526025815260200180610ccc6025913960400191505060405180910390fd5b6005805460ff19166001179055426007819055600a54600954020160085560408051348152905133917fb6b557fe9235cea11cc85bda5db86c09593e7f7903fee774c8bde722ec52daae919081900360200190a2506005600655600190565b6000546001600160a01b0316331461093d5760405162461bcd60e51b8152600401808060200182810382526030815260200180610d466030913960400191505060405180910390fd5b60036006556040517f118aa24572308142d7f42b6445d61a5b3c19219c170a0b7d306d0f7e5a6573a390600090a1565b6109756104bf565b1561097f57610987565b610987610676565b565b6101ab806109a483390190565b61015b80610b4f8339019056fe6080604052600560025534801561001557600080fd5b506040516101ab3803806101ab8339818101604052604081101561003857600080fd5b5080516020909101516000828155600181905560038290556040517fc092d3c5d4b54046e57c1483b350acac42388180f0f2048fe7cf544886e433479190a15050610123806100886000396000f3fe6080604052348015600f57600080fd5b5060043610603c5760003560e01c80635d1ca6311460415780637e810e281460595780638753367f14605f575b600080fd5b6047608d565b60408051918252519081900360200190f35b60476093565b607960048036036020811015607357600080fd5b50356099565b604080519115158252519081900360200190f35b60005490565b60015490565b600060035482111560e557600180548101908190556002541160e5576040517f7c54d659bf055650f48970c744c98932fcb42694a40d5a19cf1bd5f2076c233890600090a150600060e9565b5060015b91905056fea265627a7a7230582074814e14c91f98f1e282bd416f96ad63e7aebf00e17f3d748aa97b16de909df864736f6c634300050900326080604052600560025534801561001557600080fd5b5060405161015b38038061015b8339818101604052604081101561003857600080fd5b5080516020909101516000828155600181905560038290556040517fc092d3c5d4b54046e57c1483b350acac42388180f0f2048fe7cf544886e433479190a1505060d4806100876000396000f3fe6080604052348015600f57600080fd5b5060043610603c5760003560e01c80635d1ca6311460415780637e810e281460595780638753367f14605f575b600080fd5b6047608d565b60408051918252519081900360200190f35b60476093565b607960048036036020811015607357600080fd5b50356099565b604080519115158252519081900360200190f35b60005490565b60015490565b5060019056fea265627a7a7230582028b0efcca67afab442d754d8de3cb8463fa2e52d7836faebb4c198be0ecf9e4b64736f6c634300050900325468652076616c7565206d75737420657175616c2074686520534c4120707269636554686520534c41206d757374206861766520737461747573203320284163636570746564294f6e6c7920746865204d6f6e69746f72696e6720536572766963652063616e2063616c6c20746869732066756e6374696f6e54686520636f6e7472616374206d757374206e6f7420626520616374697665207965744f6e6c792074686520536572766963652050726f76696465722063616e2063616c6c20746869732066756e6374696f6e4f6e6c792074686520637573746f6d65722063616e2063616c6c20746869732066756e6374696f6ea265627a7a72305820e08163587037d81a7a739a2d4bf8085cdee5154a2731037a3a9f6fba80ad236c64736f6c63430005090032546865205350206d757374206e6f7420626520746865206d6f6e69746f72696e672073657276696365";

    public static final String FUNC_ISSLOPRESENT = "isSLOpresent";

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

    public static final String FUNC_GETMONITORINGSERVICE = "getMonitoringService";

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

    public RemoteCall<Boolean> isSLOpresent(BigInteger _id) {
        final Function function = new Function(FUNC_ISSLOPRESENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public RemoteCall<String> getMonitoringService() {
        final Function function = new Function(FUNC_GETMONITORINGSERVICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _customer, String _monitoringService, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.Address(_monitoringService), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _customer, String _monitoringService, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.Address(_monitoringService), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _customer, String _monitoringService, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.Address(_monitoringService), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_daysOfValidity)));
        return deployRemoteCall(SimpleSLA.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SimpleSLA> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _customer, String _monitoringService, BigInteger _price, BigInteger _daysOfValidity) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customer), 
                new org.web3j.abi.datatypes.Address(_monitoringService), 
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
