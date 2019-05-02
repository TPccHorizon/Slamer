package ch.uzh.slamer.backend.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/welcome")
    public String helloWorld() {
        return "Hello World";
    }
}
