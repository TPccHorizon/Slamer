package ch.uzh.slamer.backend.model.pojo;

import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;

public class SlaForMonitoring {

    private Sla sla;
    private SlaUser monitoringSolution;

    public SlaForMonitoring(Sla sla, SlaUser monitoringSolution) {
        this.sla = sla;
        this.monitoringSolution = monitoringSolution;
    }

    public Sla getSla() {
        return sla;
    }

    public SlaUser getMonitoringSolution() {
        return monitoringSolution;
    }
}
