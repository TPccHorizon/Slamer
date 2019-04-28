package ch.uzh.slamer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.uzh.slamer.service.WelcomeService;

@CrossOrigin(allowCredentials = "true")
@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService service ;

    @RequestMapping("/welcome")
    public String welcome() {
        return service.getWelcomeMessage();
    }
}
