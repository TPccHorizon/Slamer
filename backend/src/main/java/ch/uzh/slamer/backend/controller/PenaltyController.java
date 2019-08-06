package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.service.PenaltyService;
import codegen.tables.pojos.Penalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
public class PenaltyController {

    @Autowired
    PenaltyService penaltyService;

    @RequestMapping(method = RequestMethod.POST, path = "/penalty")
    public ResponseEntity<Boolean> addPenalty(@RequestBody Penalty penalty){
        Penalty result = penaltyService.addToSlo(penalty);
        if (result == null) {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
}
