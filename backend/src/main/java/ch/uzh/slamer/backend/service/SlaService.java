package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.enums.LifecyclePhase;
import ch.uzh.slamer.backend.model.enums.SlaStatus;
import ch.uzh.slamer.backend.model.pojo.SlaWithCustomer;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import codegen.tables.records.SlaRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlaService {

    @Autowired
    SlaUserRepository slaUserRepository;

    @Autowired
    SlaRepository slaRepository;

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


}
