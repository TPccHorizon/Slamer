package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import codegen.tables.pojos.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationService {

    @Autowired
    PasswordEncryptionService service;

    @Autowired
    private SlaUserRepository repository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

//    public SlaUser registerNewUser(SlaUser user) {
//        SlaUser existingUser;
//
//        System.out.println("Registering User");
//        System.out.println(user.getUsername());
//        try {
//            existingUser = repository.findByUsername(user.getUsername());
//            System.out.println("Existing user found with Username " + user.getUsername());
//        } catch (RecordNotFoundException e) {
//            SlaUser safeUser = getSafeUser(user);
//            System.out.println("User does not yet exist. Creating one now..");
//            return repository.add(safeUser);
//        }
//        return existingUser;
//    }
//
//    private SlaUser getSafeUser(SlaUser user) {
//        return new SlaUser(null, bCryptPasswordEncoder.encode(user.getPassword()), user.getPhoneNr(),
//                user.getUsername(), user.getPartyType(), user.getPartyName());
//    }

    public boolean checkUserCredentials(String password, SlaUser user) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

}
