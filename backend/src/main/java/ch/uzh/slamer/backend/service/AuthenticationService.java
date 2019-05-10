package ch.uzh.slamer.backend.service;

import codegen.tables.pojos.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationService {

    @Autowired
    PasswordEncryptionService service;

    public SlaUser getSafeUser(SlaUser user) {
        String salt = service.generateSalt(512).get();
        String key = service.hashPassword(user.getPassword(), salt).get();

        boolean isValidKey = service.verifyPassword(user.getPassword(), key, salt);
        if (isValidKey) {
            return new SlaUser(user.getId(), key, salt, user.getPhoneNr(),
                    user.getUsername(), user.getPartyType(), user.getPartyName());

        } else {
            return null;
        }
    }

    public boolean checkUserCredentials(String password, SlaUser user) {
        return service.verifyPassword(password, user.getPassword(), user.getSalt());
    }

}
