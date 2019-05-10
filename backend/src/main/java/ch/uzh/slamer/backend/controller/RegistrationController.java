package ch.uzh.slamer.backend.controller;


import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import ch.uzh.slamer.backend.repository.JooqSlaUserRepository;
import ch.uzh.slamer.backend.service.AuthenticationService;
import codegen.tables.pojos.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "true")
@RestController
public class RegistrationController {

    @Autowired
    private JooqSlaUserRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, path = "/users/register")
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
}
