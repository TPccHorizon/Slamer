package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.model.dto.*;
import ch.uzh.slamer.backend.model.pojo.Report;
import ch.uzh.slamer.backend.model.pojo.SlaState;
import ch.uzh.slamer.backend.model.pojo.SlaWithCustomer;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import ch.uzh.slamer.backend.rule.SlaRule;
import ch.uzh.slamer.backend.service.SlaService;
import ch.uzh.slamer.backend.service.SloService;
import codegen.tables.pojos.ServiceLevelObjective;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
public class SlaController {

    @Autowired
    SlaService slaService;

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    SloService sloService;

    @Autowired
    SlaUserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    SlaRule rule;

    @RequestMapping(method = RequestMethod.POST, path = "/slas")
    public ResponseEntity<Sla> createNewSla(@RequestBody SlaWithCustomer slaWithCustomer) {
        Sla createdSla = slaService.registerNewSla(slaWithCustomer);

        if (createdSla != null) {
            return new ResponseEntity<>(createdSla, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas")
    public ResponseEntity<List<SlaDTO>> getMySlas(@RequestParam("id") int userId){
        List<Sla> slas = slaRepository.getUsersSlas(userId);
        List<SlaDTO> slaDTOS = mapToSlaDtos(slas);
        return new ResponseEntity<>(slaDTOS, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas/{id}")
    public ResponseEntity<SlaDTO> getOne(@PathVariable int id) {
        Map<Sla, List<SlaUser>> slaListMap = slaRepository.getSlaWithParties(id);
        SlaDTO slaDTO = slaService.mapToSla(slaListMap);

        /* Get all Service Level Objectives */
        List<ServiceLevelObjective> slos = sloService.getSlosFromSla(slaDTO.getId());
        for (ServiceLevelObjective slo: slos) {
            ServiceLevelObjectiveDTO sloDto;
            if (slo.getSloType().equals("Uptime")) {
                sloDto = mapper.map(slo, UptimeDTO.class);
            } else if (slo.getSloType().equals("Throughput")) {
                sloDto = mapper.map(slo, ThroughputDTO.class);
            } else {
                sloDto = mapper.map(slo, AverageResponseTimeDTO.class);
            }
            slaDTO.addSlo(sloDto);
        }

        System.out.println("Got SLA");
        return new ResponseEntity<>(slaDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/slas/{id}")
    public ResponseEntity<SlaDTO> updateSLAStatus(@RequestBody String currentStatus, @PathVariable int id) {
        SlaState nextState;
        try {
            nextState = rule.getNextState(currentStatus);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        slaRepository.updateState(id, nextState);
        return getOne(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/slas/{id}/slos")
    public ResponseEntity<ServiceLevelObjectiveDTO> addSLO(@RequestBody ServiceLevelObjectiveDTO slo, @PathVariable int id) {
        System.out.println("new SLO creation");
        System.out.println(slo.getName());
        System.out.println(slo.getSloType());
        ServiceLevelObjective created = sloService.addToSla(slo);
        if (created == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        ServiceLevelObjectiveDTO response = mapper.map(created, ServiceLevelObjectiveDTO.class);
        response.setSloType(slo.getSloType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas/{id}/slos")
    public ResponseEntity<List<ServiceLevelObjectiveDTO>> getSLOs(@PathVariable int id) {
        System.out.println("Get all SLOs for SLA id " + id);
        List<ServiceLevelObjective> serviceLevelObjectives = new ArrayList<>();
        try {
            serviceLevelObjectives = sloService.getSlosFromSla(id);
        } catch (Exception e) {
            System.out.println("Failed to get SLOs");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        // map slos to dto
        List<ServiceLevelObjectiveDTO> serviceLevelObjectiveDTOS = new ArrayList<>();
        for (ServiceLevelObjective slo: serviceLevelObjectives) {
            serviceLevelObjectiveDTOS.add(mapper.map(slo, ServiceLevelObjectiveDTO.class));
        }
        return new ResponseEntity<>(serviceLevelObjectiveDTOS, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas/new")
    public ResponseEntity<Integer> countNewSlas(@RequestParam("id") int userId) {
        int newSLAs = slaRepository.countNewSLAs(userId);
        return new ResponseEntity<>(newSLAs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas/review")
    public ResponseEntity<List<SlaDTO>> getForReview(@RequestParam("id") int userId) {
        List<Sla> slas = slaRepository.getSlasForReview(userId);
        List<SlaDTO> slaDTOS = mapToSlaDtos(slas);
        return new ResponseEntity<>(slaDTOS, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/slas/report")
    public ResponseEntity<Report> getReport(@RequestParam("id") int userId) {
        Report report = slaRepository.getSlaReport(userId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    private List<SlaDTO> mapToSlaDtos(List<Sla> slas) {
        List<SlaDTO> slaDTOS = new ArrayList<>();
        for (Sla sla: slas) {
            Map<Sla, List<SlaUser>> slaListMap = slaRepository.getSlaWithParties(sla.getId());
            SlaDTO slaDTO = slaService.mapToSla(slaListMap);
            slaDTOS.add(slaDTO);
        }
        return slaDTOS;
    }


}
