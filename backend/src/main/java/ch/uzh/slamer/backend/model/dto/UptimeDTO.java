package ch.uzh.slamer.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Date;

public class UptimeDTO extends ServiceLevelObjectiveDTO {

    @JsonProperty
    private BigDecimal percentageOfAvailability;

    public UptimeDTO() { }

    public UptimeDTO(Integer id, String name, Integer slaId, String sloType, boolean accepted, String comment, BigDecimal percentageOfAvailability) {
        super(id, name, slaId, sloType, accepted, comment);
        this.percentageOfAvailability = percentageOfAvailability;
    }

    public BigDecimal getPercentageOfAvailability() {
        return percentageOfAvailability;
    }

    public void setPercentageOfAvailability(BigDecimal percentageOfAvailability) {
        this.percentageOfAvailability = percentageOfAvailability;
    }
}

