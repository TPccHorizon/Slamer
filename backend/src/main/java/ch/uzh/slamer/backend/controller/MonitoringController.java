package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.MeasuredResponseTime;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.service.MonitoringService;
import codegen.tables.pojos.Sla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "false", origins = "${security.allow-all}")
@RestController
public class MonitoringController {

    @Autowired
    MonitoringService monitoringService;

    @RequestMapping(method = RequestMethod.POST, path = "/monitor")
    public void measureResponseTime(@RequestBody MeasuredResponseTime measured) {
        monitoringService.checkUptime(measured);
    }
}
