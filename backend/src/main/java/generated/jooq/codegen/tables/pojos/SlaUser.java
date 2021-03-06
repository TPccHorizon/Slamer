/*
 * This file is generated by jOOQ.
 */
package codegen.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SlaUser implements Serializable {

    private static final long serialVersionUID = -843397092;

    private Integer id;
    private String  password;
    private String  username;
    private String  partyType;
    private String  partyName;
    private String  wallet;
    private String  privateKey;

    public SlaUser() {}

    public SlaUser(SlaUser value) {
        this.id = value.id;
        this.password = value.password;
        this.username = value.username;
        this.partyType = value.partyType;
        this.partyName = value.partyName;
        this.wallet = value.wallet;
        this.privateKey = value.privateKey;
    }

    public SlaUser(
        Integer id,
        String  password,
        String  username,
        String  partyType,
        String  partyName,
        String  wallet,
        String  privateKey
    ) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.partyType = partyType;
        this.partyName = partyName;
        this.wallet = wallet;
        this.privateKey = privateKey;
    }

    public Integer getId() {
        return this.id;
    }

    public SlaUser setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public SlaUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public SlaUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPartyType() {
        return this.partyType;
    }

    public SlaUser setPartyType(String partyType) {
        this.partyType = partyType;
        return this;
    }

    public String getPartyName() {
        return this.partyName;
    }

    public SlaUser setPartyName(String partyName) {
        this.partyName = partyName;
        return this;
    }

    public String getWallet() {
        return this.wallet;
    }

    public SlaUser setWallet(String wallet) {
        this.wallet = wallet;
        return this;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public SlaUser setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SlaUser other = (SlaUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        if (partyType == null) {
            if (other.partyType != null)
                return false;
        }
        else if (!partyType.equals(other.partyType))
            return false;
        if (partyName == null) {
            if (other.partyName != null)
                return false;
        }
        else if (!partyName.equals(other.partyName))
            return false;
        if (wallet == null) {
            if (other.wallet != null)
                return false;
        }
        else if (!wallet.equals(other.wallet))
            return false;
        if (privateKey == null) {
            if (other.privateKey != null)
                return false;
        }
        else if (!privateKey.equals(other.privateKey))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.partyType == null) ? 0 : this.partyType.hashCode());
        result = prime * result + ((this.partyName == null) ? 0 : this.partyName.hashCode());
        result = prime * result + ((this.wallet == null) ? 0 : this.wallet.hashCode());
        result = prime * result + ((this.privateKey == null) ? 0 : this.privateKey.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SlaUser (");

        sb.append(id);
        sb.append(", ").append(password);
        sb.append(", ").append(username);
        sb.append(", ").append(partyType);
        sb.append(", ").append(partyName);
        sb.append(", ").append(wallet);
        sb.append(", ").append(privateKey);

        sb.append(")");
        return sb.toString();
    }
}
