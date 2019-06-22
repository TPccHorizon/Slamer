package ch.uzh.slamer.backend.model.dto;

import codegen.tables.pojos.SlaReview;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReviewDTO {

    @JsonProperty
    private int slaId;

    @JsonProperty
    private SlaReview validFrom;

    @JsonProperty
    private SlaReview validTo;

    @JsonProperty
    private SlaReview servicePrice;

    @JsonProperty
    private List<ServiceLevelObjectiveDTO> slos;

    public ReviewDTO() {
    }

    public ReviewDTO(int slaId, SlaReview validFrom, SlaReview validTo, SlaReview servicePrice, List<ServiceLevelObjectiveDTO> slos) {
        this.slaId = slaId;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.servicePrice = servicePrice;
        this.slos = slos;
    }

    public int getSlaId() {
        return slaId;
    }

    public SlaReview getValidFrom() {
        return validFrom;
    }

    public SlaReview getValidTo() {
        return validTo;
    }

    public SlaReview getServicePrice() {
        return servicePrice;
    }

    public List<ServiceLevelObjectiveDTO> getSlos() {
        return slos;
    }

    public void setSlaId(int slaId) {
        this.slaId = slaId;
    }

    public void setValidFrom(SlaReview validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(SlaReview validTo) {
        this.validTo = validTo;
    }

    public void setServicePrice(SlaReview servicePrice) {
        this.servicePrice = servicePrice;
    }

    public void setSlos(List<ServiceLevelObjectiveDTO> slos) {
        this.slos = slos;
    }
}
