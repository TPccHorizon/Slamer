package ch.uzh.slamer.backend.smartcontract;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

public class SLAContractManager {

    public SLAContractManager() {
    }

    public String connect() {
        Web3j web3j = Web3j.build(new HttpService());

//        TransactionManager transactionManager = new RawTransactionManager(
//                web3j,
//                getCredentialsFromKey()
//        );

        return printWeb3Version(web3j);

    }

    private Credentials getCredentialsFromKey() {
        return Credentials.create("66b5e0227c081791d90c9bd8f537c252b628f2202f8efcac71f550084151b271");
    }

    private String printWeb3Version(Web3j web3j) {
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String web3jVersion = "";
        if (web3ClientVersion != null) {
            web3jVersion = web3ClientVersion.getWeb3ClientVersion();
        } else {
            web3jVersion = "Not available";
        }
        System.out.println("Web3 Client Version: " + web3jVersion);
        return "Web3 Client Version: " + web3jVersion;
    }
}