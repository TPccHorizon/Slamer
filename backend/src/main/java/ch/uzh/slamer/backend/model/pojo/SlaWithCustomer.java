package ch.uzh.slamer.backend.model.pojo;

import codegen.tables.pojos.Sla;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SlaWithCustomer {

    @JsonProperty
    private Sla sla;

    @JsonProperty
    private String customerUsername;

    @JsonProperty
    private String providerUsername;

    public SlaWithCustomer() { }

    public Sla getSla() {
        return sla;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public String getProviderUsername() {
        return providerUsername;
    }
}
