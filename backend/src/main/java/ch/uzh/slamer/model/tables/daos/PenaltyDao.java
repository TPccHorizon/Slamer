/*
 * This file is generated by jOOQ.
 */
package ch.uzh.slamer.model.tables.daos;


import ch.uzh.slamer.model.tables.Penalty;
import ch.uzh.slamer.model.tables.records.PenaltyRecord;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class PenaltyDao extends DAOImpl<PenaltyRecord, ch.uzh.slamer.model.tables.pojos.Penalty, Integer> {

    /**
     * Create a new PenaltyDao without any configuration
     */
    public PenaltyDao() {
        super(Penalty.PENALTY, ch.uzh.slamer.model.tables.pojos.Penalty.class);
    }

    /**
     * Create a new PenaltyDao with an attached configuration
     */
    public PenaltyDao(Configuration configuration) {
        super(Penalty.PENALTY, ch.uzh.slamer.model.tables.pojos.Penalty.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(ch.uzh.slamer.model.tables.pojos.Penalty object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<ch.uzh.slamer.model.tables.pojos.Penalty> fetchById(Integer... values) {
        return fetch(Penalty.PENALTY.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public ch.uzh.slamer.model.tables.pojos.Penalty fetchOneById(Integer value) {
        return fetchOne(Penalty.PENALTY.ID, value);
    }

    /**
     * Fetch records that have <code>percentage IN (values)</code>
     */
    public List<ch.uzh.slamer.model.tables.pojos.Penalty> fetchByPercentage(BigDecimal... values) {
        return fetch(Penalty.PENALTY.PERCENTAGE, values);
    }

    /**
     * Fetch records that have <code>reference_value IN (values)</code>
     */
    public List<ch.uzh.slamer.model.tables.pojos.Penalty> fetchByReferenceValue(BigDecimal... values) {
        return fetch(Penalty.PENALTY.REFERENCE_VALUE, values);
    }

    /**
     * Fetch records that have <code>slo_id IN (values)</code>
     */
    public List<ch.uzh.slamer.model.tables.pojos.Penalty> fetchBySloId(Integer... values) {
        return fetch(Penalty.PENALTY.SLO_ID, values);
    }
}
