/*
 * This file is generated by jOOQ.
 */
package ch.uzh.slamer.model.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Penalty implements Serializable {

    private static final long serialVersionUID = 1816841112;

    private Integer    id;
    private BigDecimal percentage;
    private BigDecimal referenceValue;
    private Integer    sloId;

    public Penalty() {}

    public Penalty(Penalty value) {
        this.id = value.id;
        this.percentage = value.percentage;
        this.referenceValue = value.referenceValue;
        this.sloId = value.sloId;
    }

    public Penalty(
        Integer    id,
        BigDecimal percentage,
        BigDecimal referenceValue,
        Integer    sloId
    ) {
        this.id = id;
        this.percentage = percentage;
        this.referenceValue = referenceValue;
        this.sloId = sloId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPercentage() {
        return this.percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public BigDecimal getReferenceValue() {
        return this.referenceValue;
    }

    public void setReferenceValue(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
    }

    public Integer getSloId() {
        return this.sloId;
    }

    public void setSloId(Integer sloId) {
        this.sloId = sloId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Penalty (");

        sb.append(id);
        sb.append(", ").append(percentage);
        sb.append(", ").append(referenceValue);
        sb.append(", ").append(sloId);

        sb.append(")");
        return sb.toString();
    }
}