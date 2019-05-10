package ch.uzh.slamer.backend.model.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginData {

    @JsonProperty("username")
    private String userName;

    @JsonProperty
    private String password;

    public LoginData() { }

    public LoginData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
