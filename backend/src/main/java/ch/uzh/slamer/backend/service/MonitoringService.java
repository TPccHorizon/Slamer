package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.MeasuredResponseTime;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import ch.uzh.slamer.backend.smartcontract.SLAContractManager;
import codegen.tables.pojos.Sla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    SlaUserRepository slaUserRepository;

    public void checkUptime(MeasuredResponseTime measured) {
        Sla sla = getSLA(measured.getSlaId());
    }

    private Sla getSLA(int slaId) {
        Sla sla = null;
        try {
            sla = slaRepository.findById(slaId);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("SLA not found with id " + slaId);
        }
        return sla;
    }

    private SLAContractManager getContractManager(Sla sla){
        return null;
    }
}
