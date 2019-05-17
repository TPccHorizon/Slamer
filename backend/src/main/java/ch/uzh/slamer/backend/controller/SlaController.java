package ch.uzh.slamer.backend.controller;

import codegen.tables.pojos.Sla;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
@RequestMapping("/sla")
public class SlaController {

    public ResponseEntity<Sla> createNewSla(@RequestBody Sla sla) {
        return null;
        // implement repository and Service
    }
}
