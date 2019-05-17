package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.model.pojo.SlaWithCustomer;
import ch.uzh.slamer.backend.service.SlaService;
import codegen.tables.pojos.Sla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
@RequestMapping("/sla")
public class SlaController {

    @Autowired
    SlaService slaService;

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<Sla> createNewSla(@RequestBody SlaWithCustomer slaWithCustomer) {
        Sla createdSla = slaService.registerNewSla(slaWithCustomer);

        if (createdSla != null) {
            return new ResponseEntity<>(createdSla, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
