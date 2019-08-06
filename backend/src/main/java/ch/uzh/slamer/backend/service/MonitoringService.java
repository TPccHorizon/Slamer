package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.MeasuredResponseTime;
import ch.uzh.slamer.backend.model.pojo.SlaForMonitoring;
import ch.uzh.slamer.backend.repository.SlaRepository;
import ch.uzh.slamer.backend.repository.SlaUserRepository;
import ch.uzh.slamer.backend.smartcontract.SLAContractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    SlaUserRepository slaUserRepository;

    public boolean checkResponseTime(MeasuredResponseTime measured) {
        SlaForMonitoring sla = getSLA(measured.getSlaId());
        if (!isCorrectWallet(sla, measured)) {
            return false;
        }
        SLAContractManager contractManager = getContractManager(sla);
        contractManager.validateResponseTime(measured, sla);
        return true;
    }

    private SlaForMonitoring getSLA(int slaId) {
        SlaForMonitoring sla = null;
        try {
            sla = slaRepository.getSlaForMonitoring(slaId);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            System.out.println("SLA not found with id " + slaId);
        }
        return sla;
    }

    private SLAContractManager getContractManager(SlaForMonitoring sla){
        /* Initialize contract manager with monitoring solution account */
        return new SLAContractManager(sla.getMonitoringSolution().getPrivateKey());
    }

    private boolean isCorrectWallet(SlaForMonitoring sla, MeasuredResponseTime measured) {
        return sla.getMonitoringSolution().getWallet().equals(measured.getWallet());
    }
}
