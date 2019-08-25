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

import java.util.List;
import java.util.stream.Collectors;

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
            user.setWallet(userDTO.getWallet().toLowerCase());
            user.setPrivateKey(userDTO.getPrivateKey());
            userRepository.update(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            System.out.println("User not found with id: " + id);
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "users/monitor")
    public ResponseEntity<List<SlaUserDTO>> getMonitoringServices() {
        List<SlaUser> monitoringServices = userRepository.getMonitoringServices();
        List<SlaUserDTO> serviceDtos = monitoringServices.stream().map(service -> mapper.map(service, SlaUserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviceDtos, HttpStatus.OK);
    }
}
