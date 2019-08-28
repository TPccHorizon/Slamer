package ch.uzh.slamer.backend.model.dto;

import codegen.tables.pojos.Sla;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SlaDTO extends Sla {

    @JsonProperty
    private SlaUserDTO serviceCustomer;

    @JsonProperty
    private SlaUserDTO serviceProvider;

    @JsonProperty
    private SlaUserDTO monitoringService;

    @JsonProperty
    private List<ServiceLevelObjectiveDTO> slos = new ArrayList<>();

    public SlaDTO(){}

    public SlaUserDTO getServiceCustomer() {
        return serviceCustomer;
    }

    public SlaUserDTO getServiceProvider() {
        return serviceProvider;
    }

    public SlaUserDTO getMonitoringService() { return monitoringService; }

    public void addSlo(ServiceLevelObjectiveDTO slo) {this.slos.add(slo);}

    public void setServiceCustomer(SlaUserDTO serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    public void setServiceProvider(SlaUserDTO serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void setMonitoringService(SlaUserDTO monitoringService) {
        this.monitoringService = monitoringService;
    }

    public List<ServiceLevelObjectiveDTO> getSlos() {
        return slos;
    }

    public void setSlos(List<ServiceLevelObjectiveDTO> slos) {
        this.slos = slos;
    }
}
