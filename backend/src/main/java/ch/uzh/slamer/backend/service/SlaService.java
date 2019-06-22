package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.ReviewDTO;
import ch.uzh.slamer.backend.model.dto.SlaDTO;
import ch.uzh.slamer.backend.model.dto.SlaUserDTO;
import ch.uzh.slamer.backend.model.enums.LifecyclePhase;
import ch.uzh.slamer.backend.model.enums.SlaStatus;
import ch.uzh.slamer.backend.model.pojo.SlaWithCustomer;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import codegen.tables.records.SlaRecord;
import org.jooq.meta.derby.sys.Sys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ch.uzh.slamer.backend.model.enums.SlaStatus.*;

@Component
public class SlaService {

    @Autowired
    SlaUserRepository slaUserRepository;

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ModelMapper mapper;

    public Sla registerNewSla(SlaWithCustomer slaWithCustomer) {
        int customerId = findParty(slaWithCustomer.getCustomerUsername());
        int providerId = findParty(slaWithCustomer.getProviderUsername());
        if (customerId == -1 || providerId == -1) {
            return null;
        }
        SlaRecord slaRecord = slaRepository.createRecord(slaWithCustomer.getSla());
        slaRecord.setServiceCustomerId(customerId);
        slaRecord.setServiceProviderId(providerId);
        setInitialRecordValues(slaRecord);

        return slaRepository.add(slaRecord);
    }

    private int findParty(String username) {
        try {
            SlaUser customer = slaUserRepository.findByUsername(username);
            return customer.getId();
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void setInitialRecordValues(SlaRecord record) {
        LifecyclePhase initialPhase = LifecyclePhase.DEFINITION;
        SlaStatus initialStatus = SlaStatus.IDENTIFIED;
        record.setStatus(initialStatus.getStatus())
                .setLifecyclePhase(initialPhase.getPhase());
    }

    public SlaDTO mapToSla(Map<Sla, List<SlaUser>> slaListMap) {
        SlaDTO slaDTO = null;
        List<SlaUser> parties = new LinkedList<>();
        for (Map.Entry<Sla, List<SlaUser>> slaListEntry: slaListMap.entrySet()) {
            slaDTO = mapper.map(slaListEntry.getKey(), SlaDTO.class);
            parties = slaListEntry.getValue();
        }
        if (slaDTO == null) {
            return null;
        }

        /* Set parties as Customer and Provider respectively */
        for (SlaUser party: parties) {
            if (party.getId().equals(slaDTO.getServiceCustomerId())) {
                slaDTO.setServiceCustomer(mapper.map(party, SlaUserDTO.class));
            } else {
                slaDTO.setServiceProvider(mapper.map(party, SlaUserDTO.class));
            }
        }
        return slaDTO;
    }


}
