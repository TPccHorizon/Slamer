package ch.uzh.slamer.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlaUserDTO {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String wallet;

    @JsonProperty
    private String privateKey;

    @JsonProperty
    private String  username;

    @JsonProperty
    private String  partyName;

    public SlaUserDTO() { }

    public SlaUserDTO(Integer id, String wallet, String privateKey, String username, String partyName) {
        this.id = id;
        this.wallet = wallet;
        this.privateKey = privateKey;
        this.username = username;
        this.partyName = partyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}
