/*
 * This file is generated by jOOQ.
 */
package codegen.tables.records;


import codegen.tables.Penalty;

import java.math.BigDecimal;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PenaltyRecord extends UpdatableRecordImpl<PenaltyRecord> implements Record4<Integer, BigDecimal, BigDecimal, Integer> {

    private static final long serialVersionUID = 487440336;

    /**
     * Setter for <code>public.penalty.id</code>.
     */
    public PenaltyRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.penalty.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.penalty.percentage</code>.
     */
    public PenaltyRecord setPercentage(BigDecimal value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.penalty.percentage</code>.
     */
    public BigDecimal getPercentage() {
        return (BigDecimal) get(1);
    }

    /**
     * Setter for <code>public.penalty.reference_value</code>.
     */
    public PenaltyRecord setReferenceValue(BigDecimal value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.penalty.reference_value</code>.
     */
    public BigDecimal getReferenceValue() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>public.penalty.slo_id</code>.
     */
    public PenaltyRecord setSloId(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.penalty.slo_id</code>.
     */
    public Integer getSloId() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, BigDecimal, BigDecimal, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, BigDecimal, BigDecimal, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Penalty.PENALTY.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field2() {
        return Penalty.PENALTY.PERCENTAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field3() {
        return Penalty.PENALTY.REFERENCE_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Penalty.PENALTY.SLO_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component2() {
        return getPercentage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component3() {
        return getReferenceValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getSloId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value2() {
        return getPercentage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value3() {
        return getReferenceValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getSloId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PenaltyRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PenaltyRecord value2(BigDecimal value) {
        setPercentage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PenaltyRecord value3(BigDecimal value) {
        setReferenceValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PenaltyRecord value4(Integer value) {
        setSloId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PenaltyRecord values(Integer value1, BigDecimal value2, BigDecimal value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PenaltyRecord
     */
    public PenaltyRecord() {
        super(Penalty.PENALTY);
    }

    /**
     * Create a detached, initialised PenaltyRecord
     */
    public PenaltyRecord(Integer id, BigDecimal percentage, BigDecimal referenceValue, Integer sloId) {
        super(Penalty.PENALTY);

        set(0, id);
        set(1, percentage);
        set(2, referenceValue);
        set(3, sloId);
    }
}
