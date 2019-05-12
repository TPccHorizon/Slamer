package ch.uzh.slamer.backend.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

    @Value("${security.jwt.signup:/users/register}")
    private String signUp;

    @Value("${security.jwt.login:/users/login}")
    private String login;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;


    public String getSignUp() {
        return signUp;
    }

    public String getLogin() {
        return login;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }
}
