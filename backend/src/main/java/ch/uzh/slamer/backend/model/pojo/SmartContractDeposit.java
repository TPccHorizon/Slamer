package ch.uzh.slamer.backend.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmartContractDeposit {

    @JsonProperty
    private int customerId;

    @JsonProperty
    private int slaPrice;

    @JsonProperty
    private int slaId;

    public SmartContractDeposit(int customerId, int slaPrice, int slaId) {
        this.customerId = customerId;
        this.slaPrice = slaPrice;
        this.slaId = slaId;
    }

    public SmartContractDeposit() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getSlaPrice() {
        return slaPrice;
    }

    public int getSlaId() {
        return slaId;
    }
}
