package ch.uzh.slamer.backend.controller;


import ch.uzh.slamer.backend.repository.JooqSlaUserRepository;
import ch.uzh.slamer.backend.service.RegistrationService;
import codegen.tables.pojos.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {

    @Autowired
    private JooqSlaUserRepository repository;

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public SlaUser register(@RequestBody SlaUser slaUser) {
        return repository.add(slaUser);
    }
}
