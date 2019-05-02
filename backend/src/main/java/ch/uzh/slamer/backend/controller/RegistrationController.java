package ch.uzh.slamer.backend.controller;


import ch.uzh.slamer.backend.service.RegistrationService;
import codegen.tables.pojos.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public SlaUser register(@RequestBody SlaUser slaUser) {
        return service.registerUser(slaUser);
    }
}
