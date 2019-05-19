package ch.uzh.slamer.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Date;

public class ThroughputDTO extends ServiceLevelObjectiveDTO {

    @JsonProperty
    private String     throughputDataUnit;

    @JsonProperty
    private BigDecimal throughputDataSize;

    @JsonProperty
    private String     relationalOperator;

    @JsonProperty
    private BigDecimal throughputThresholdValue;

    @JsonProperty
    private String     timeUnit;

    public ThroughputDTO() { }

    public ThroughputDTO(Integer id, String name, Date validFrom, Date validTo, Integer slaId, String sloType, String throughputDataUnit, BigDecimal throughputDataSize, String relationalOperator, BigDecimal throughputThresholdValue, String timeUnit) {
        super(id, name, validFrom, validTo, slaId, sloType);
        this.throughputDataUnit = throughputDataUnit;
        this.throughputDataSize = throughputDataSize;
        this.relationalOperator = relationalOperator;
        this.throughputThresholdValue = throughputThresholdValue;
        this.timeUnit = timeUnit;
    }

    public String getThroughputDataUnit() {
        return throughputDataUnit;
    }

    public void setThroughputDataUnit(String throughputDataUnit) {
        this.throughputDataUnit = throughputDataUnit;
    }

    public BigDecimal getThroughputDataSize() {
        return throughputDataSize;
    }

    public void setThroughputDataSize(BigDecimal throughputDataSize) {
        this.throughputDataSize = throughputDataSize;
    }

    public String getRelationalOperator() {
        return relationalOperator;
    }

    public void setRelationalOperator(String relationalOperator) {
        this.relationalOperator = relationalOperator;
    }

    public BigDecimal getThroughputThresholdValue() {
        return throughputThresholdValue;
    }

    public void setThroughputThresholdValue(BigDecimal throughputThresholdValue) {
        this.throughputThresholdValue = throughputThresholdValue;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }
}
