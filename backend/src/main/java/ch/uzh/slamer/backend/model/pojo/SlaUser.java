package ch.uzh.slamer.backend.model.pojo;

import javax.persistence.Column;

public class SlaUser {

    @Column
    private int id;

    @Column
    private String password;

    @Column
    private String salt;

    @Column(name = "phone_nr")
    private String phoneNr;

    @Column
    private String username;

    @Column(name = "party_type")
    private String partyType;

    @Column(name = "party_name")
    private String partyName;

    public SlaUser() {}

    public SlaUser(int id, String password, String salt, String phoneNr, String userName, String partyType, String partyName) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.phoneNr = phoneNr;
        this.username = userName;
        this.partyType = partyType;
        this.partyName = partyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}
