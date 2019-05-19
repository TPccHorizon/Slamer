package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.model.dto.ServiceLevelObjectiveDTO;
import ch.uzh.slamer.backend.repository.ServiceLevelObjectiveRepository;
import codegen.tables.pojos.ServiceLevelObjective;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SloService {

    @Autowired
    ServiceLevelObjectiveRepository sloRepository;

    @Autowired
    ModelMapper mapper;

    public ServiceLevelObjective addToSla(ServiceLevelObjectiveDTO slo) {
        int sloTypeId = sloRepository.getSloTypeId(slo.getSloType());
        ServiceLevelObjective serviceLevelObjective = mapper.map(slo, ServiceLevelObjective.class);
        serviceLevelObjective.setSloTypeId(sloTypeId);
        try {
            return sloRepository.add(serviceLevelObjective);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
