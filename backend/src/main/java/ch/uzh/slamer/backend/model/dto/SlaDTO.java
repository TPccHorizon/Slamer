package ch.uzh.slamer.backend.model.dto;

import codegen.tables.pojos.Sla;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SlaDTO extends Sla {

    @JsonProperty
    private SlaUserDTO serviceCustomer;

    @JsonProperty
    private SlaUserDTO serviceProvider;

    public SlaDTO(){}

    public SlaUserDTO getServiceCustomer() {
        return serviceCustomer;
    }

    public SlaUserDTO getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceCustomer(SlaUserDTO serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    public void setServiceProvider(SlaUserDTO serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
