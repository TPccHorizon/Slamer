package ch.uzh.slamer.backend.model.dto;

import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SlaDTO extends Sla {

    @JsonProperty
    private SlaUserDTO serviceCustomer;

    @JsonProperty
    private SlaUserDTO serviceProvider;

    public SlaDTO(){}

    public SlaDTO(Sla sla, List<SlaUserDTO> parties) {
        super(sla.getId(), sla.getStatus(), sla.getServicePrice(), sla.getLifecyclePhase(), sla.getValidFrom(),
                sla.getValidTo(), sla.getServiceProviderId(), sla.getServiceCustomerId());
        for (SlaUserDTO user: parties) {
            if (user.getId().equals(super.getServiceCustomerId())) {
                serviceCustomer = user;
            } else if (user.getId().equals(super.getServiceProviderId())) {
                serviceProvider = user;
            } else {
                serviceProvider = null;
                serviceCustomer = null;
            }
        }
    }

    public SlaUserDTO getServiceCustomer() {
        return serviceCustomer;
    }

    public SlaUserDTO getServiceProvider() {
        return serviceProvider;
    }
}
