package ch.uzh.slamer.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ServiceLevelObjectiveDTO {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Date validFrom;

    @JsonProperty
    private Date validTo;

    @JsonProperty
    private Integer slaId;

    @JsonProperty
    private String sloType;

    public ServiceLevelObjectiveDTO() { }

    public ServiceLevelObjectiveDTO(Integer id, String name, Date validFrom, Date validTo, Integer slaId, String sloType) {
        this.id = id;
        this.name = name;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.slaId = slaId;
        this.sloType = sloType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Integer getSlaId() {
        return slaId;
    }

    public void setSlaId(Integer slaId) {
        this.slaId = slaId;
    }

    public String getSloType() {
        return sloType;
    }

    public void setSloType(String sloType) {
        this.sloType = sloType;
    }
}
