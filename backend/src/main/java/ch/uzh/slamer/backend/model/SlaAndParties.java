package ch.uzh.slamer.backend.model;

import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SlaAndParties extends Sla {

    @JsonProperty
    private SlaUser serviceCustomer;

    @JsonProperty
    private SlaUser serviceProvider;

    public SlaAndParties(){}

    public SlaAndParties(Sla sla, List<SlaUser> parties) {
        super(sla.getId(), sla.getStatus(), sla.getServicePrice(), sla.getLifecyclePhase(), sla.getValidFrom(),
                sla.getValidTo(), sla.getServiceProviderId(), sla.getServiceCustomerId());
        for (SlaUser user: parties) {
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

    public SlaUser getServiceCustomer() {
        return serviceCustomer;
    }

    public SlaUser getServiceProvider() {
        return serviceProvider;
    }
}
