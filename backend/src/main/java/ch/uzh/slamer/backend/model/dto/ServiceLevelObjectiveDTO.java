package ch.uzh.slamer.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.sql.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "sloType", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = UptimeDTO.class, name = "Uptime"),
                @JsonSubTypes.Type(value = ThroughputDTO.class, name = "Throughput"),
                @JsonSubTypes.Type(value = AverageResponseTimeDTO.class, name = "Average Response Time")})
public class ServiceLevelObjectiveDTO {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Integer slaId;

    @JsonProperty
    private String sloType;

    @JsonProperty
    private boolean accepted;

    @JsonProperty
    private String comment;

    public ServiceLevelObjectiveDTO() { }

    public ServiceLevelObjectiveDTO(Integer id, String name, Integer slaId, String sloType) {
        this.id = id;
        this.name = name;
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
