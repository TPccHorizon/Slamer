package ch.uzh.slamer.backend.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmartContractDeployment {

    @JsonProperty
    private int serviceProviderId;

    @JsonProperty
    private int slaId;

    public SmartContractDeployment() {
    }

    public SmartContractDeployment(int serviceProviderId, int slaId) {
        this.serviceProviderId = serviceProviderId;
        this.slaId = slaId;
    }

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public int getSlaId() {
        return slaId;
    }
}
