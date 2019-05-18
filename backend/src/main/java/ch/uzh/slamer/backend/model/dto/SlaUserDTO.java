package ch.uzh.slamer.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlaUserDTO {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String  phoneNr;

    @JsonProperty
    private String  username;

    @JsonProperty
    private String  partyName;

    public SlaUserDTO() { }

    public SlaUserDTO(Integer id, String phoneNr, String username, String partyName) {
        this.id = id;
        this.phoneNr = phoneNr;
        this.username = username;
        this.partyName = partyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
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
