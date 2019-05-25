package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.model.dto.ServiceLevelObjectiveDTO;
import ch.uzh.slamer.backend.repository.ServiceLevelObjectiveRepository;
import codegen.tables.pojos.ServiceLevelObjective;
import org.jooq.meta.derby.sys.Sys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SloService {

    @Autowired
    ServiceLevelObjectiveRepository sloRepository;

    @Autowired
    ModelMapper mapper;

    public ServiceLevelObjective addToSla(ServiceLevelObjectiveDTO slo) {
        ServiceLevelObjective serviceLevelObjective = mapper.map(slo, ServiceLevelObjective.class);
        try {
            return sloRepository.add(serviceLevelObjective);
        } catch (Exception e) {
            System.out.println("Error during SLO Insertion");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<ServiceLevelObjective> getSlosFromSla(int slaId) {
        return sloRepository.getAllBySlaId(slaId);
    }
}
