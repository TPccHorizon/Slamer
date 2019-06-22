package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.smartcontract.SLAContractManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
public class SmartContractController {

    @RequestMapping(method = RequestMethod.GET, path = "/version")
    public ResponseEntity<String> printVersion() {
        System.out.println("Printing Version...");
        SLAContractManager manager = new SLAContractManager();
        return new ResponseEntity<>(manager.connect(), HttpStatus.OK);
    }
}
