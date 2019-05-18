package ch.uzh.slamer.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Date;

public class AvailabilityDTO extends ServiceLevelObjectiveDTO {

    @JsonProperty
    private BigDecimal percentageOfAvailability;

    public AvailabilityDTO() { }

    public AvailabilityDTO(Integer id, String name, Date validFrom, Date validTo, Integer slaId, String sloType, BigDecimal percentageOfAvailability) {
        super(id, name, validFrom, validTo, slaId, sloType);
        this.percentageOfAvailability = percentageOfAvailability;
    }

    public BigDecimal getPercentageOfAvailability() {
        return percentageOfAvailability;
    }

    public void setPercentageOfAvailability(BigDecimal percentageOfAvailability) {
        this.percentageOfAvailability = percentageOfAvailability;
    }
}

