package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.SlaAndParties;
import ch.uzh.slamer.backend.model.pojo.SlaWithCustomer;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import ch.uzh.slamer.backend.service.SlaService;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
//@RequestMapping("/slas")
public class SlaController {

    @Autowired
    SlaService slaService;

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    SlaUserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/slas")
    public ResponseEntity<Sla> createNewSla(@RequestBody SlaWithCustomer slaWithCustomer) {
        Sla createdSla = slaService.registerNewSla(slaWithCustomer);

        if (createdSla != null) {
            return new ResponseEntity<>(createdSla, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas")
    public ResponseEntity<List<Sla>> getMySlas(@RequestParam("user") String username){
        int userId = 0;
        try {
            userId = userRepository.findByUsername(username).getId();
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        List<Sla> slas = slaRepository.getUsersSlas(userId);
        return new ResponseEntity<>(slas, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas/{id}")
    public ResponseEntity<SlaAndParties> getOne(@PathVariable int id) {
        SlaAndParties sla = slaRepository.getSlaWithParties(id);
        if (sla != null){
            System.out.println("Got SLA");
            return new ResponseEntity<>(sla, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
