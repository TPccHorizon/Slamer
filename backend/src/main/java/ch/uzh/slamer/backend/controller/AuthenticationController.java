package ch.uzh.slamer.backend.controller;


import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import ch.uzh.slamer.backend.model.pojo.LoginData;
import ch.uzh.slamer.backend.repository.JooqSlaUserRepository;
import ch.uzh.slamer.backend.service.AuthenticationService;
import codegen.tables.pojos.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/users")
public class AuthenticationController {

    @Autowired
    private JooqSlaUserRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public SlaUser register(@RequestBody SlaUser slaUser) {
        SlaUser existingUser;
        System.out.println("Registering User");
        try {
            existingUser = repository.findByUsername(slaUser.getUsername());
            System.out.println("Existing user found with Username " + slaUser.getUsername());
        } catch (SlaUserNotFoundException e) {
            SlaUser safeUser = authenticationService.getSafeUser(slaUser);
            System.out.println("User does not yet exist. Creating one now..");
            System.out.println("User has following properties:");
            System.out.println("PartyName: " + safeUser.getPartyName() + ", PartyType: " + safeUser.getPartyType());
            return repository.add(safeUser);
        }
        return existingUser;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<SlaUser> login(@RequestBody LoginData loginData) {
        SlaUser existingUser;
        try {
            existingUser = repository.findByUsername(loginData.getUserName());

        } catch (SlaUserNotFoundException e) {
            System.out.println("No user found with username: " + loginData.getUserName());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println("Found User with matching username. Now verify PW");
        boolean isValid = authenticationService.checkUserCredentials(loginData.getPassword(), existingUser);
        if (isValid) {
            System.out.println("Access Granted");
            return ResponseEntity.ok(existingUser);
        } else {
            System.out.println("Access Denied. Wrong username or password");
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
