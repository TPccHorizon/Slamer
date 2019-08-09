package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.MeasuredResponseTime;
import ch.uzh.slamer.backend.model.dto.SlaUserDTO;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.service.AuthenticationService;
import ch.uzh.slamer.backend.service.MonitoringService;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "false", origins = "${security.allow-all}")
@RestController
public class MonitoringController {

    @Autowired
    MonitoringService monitoringService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(method = RequestMethod.POST, path = "/monitor/register/{slaId}")
    public Boolean registerMonitoringService(@RequestBody SlaUserDTO monitoringService, @PathVariable int slaId) {
        Sla sla;
        try {
            sla = slaRepository.findById(slaId);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("No SLA found with ID " + slaId);
            return false;
        }
        SlaUser monitoringUser = mapper.map(monitoringService, SlaUser.class);
        monitoringUser.setPartyType("Monitoring");
        monitoringUser.setUsername("Monitor_" + slaId);
        authenticationService.registerNewUser(monitoringUser);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/monitor")
    public Boolean measureResponseTime(@RequestBody MeasuredResponseTime measured) {
        return monitoringService.checkResponseTime(measured);
    }
}
