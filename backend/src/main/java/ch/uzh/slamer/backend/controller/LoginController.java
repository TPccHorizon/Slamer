package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import ch.uzh.slamer.backend.model.pojo.LoginData;
import ch.uzh.slamer.backend.repository.JooqSlaUserRepository;
import ch.uzh.slamer.backend.service.AuthenticationService;
import ch.uzh.slamer.backend.service.PasswordEncryptionService;
import codegen.tables.pojos.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "true")
@RestController
public class LoginController {

    @Autowired
    private JooqSlaUserRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, path = "/users/login")
    public SlaUser login(@RequestBody LoginData loginData) {
        SlaUser existingUser;
        try {
            existingUser = repository.findByUsername(loginData.getUserName());

        } catch (SlaUserNotFoundException e) {
            System.out.println("No user found with username: " + loginData.getUserName());
            return null;
        }
        System.out.println("Found User with matching username. Now verify PW");
        boolean isValid = authenticationService.checkUserCredentials(loginData.getPassword(), existingUser);
        if (isValid) {
            System.out.println("Access Granted");
            return existingUser;
        } else {
            System.out.println("Access Denied. Wrong username or password");
            return null;
        }
    }
}
