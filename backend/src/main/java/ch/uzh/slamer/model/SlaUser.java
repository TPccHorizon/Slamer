package ch.uzh.slamer.model;


import javax.persistence.Column;

public class SlaUser {

    @Column(name = "id")
    private int id;

    @Column(name = "password")
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

    public SlaUser(int id, String password, String salt, String phoneNr, String username, String partyType, String partyName) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.phoneNr = phoneNr;
        this.username = username;
        this.partyType = partyType;
        this.partyName = partyName;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public String getUsername() {
        return username;
    }

    public String getPartyType() {
        return partyType;
    }

    public String getPartyName() {
        return partyName;
    }
}
