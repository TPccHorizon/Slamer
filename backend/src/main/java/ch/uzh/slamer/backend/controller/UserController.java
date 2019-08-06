package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.SlaUserDTO;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import codegen.tables.pojos.SlaUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
public class UserController {

    @Autowired
    SlaUserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(method = RequestMethod.PUT, path = "users/{id}")
    public ResponseEntity<Boolean> updateWallet(@RequestBody SlaUserDTO userDTO, @PathVariable int id) {
        try {
            SlaUser user = userRepository.findById(id);
            user.setWallet(userDTO.getWallet());
            user.setPrivateKey(userDTO.getPrivateKey());
            userRepository.update(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            System.out.println("User not found with id: " + id);
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

    }
}
