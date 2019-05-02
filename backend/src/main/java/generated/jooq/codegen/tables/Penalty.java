/*
 * This file is generated by jOOQ.
 */
package codegen.tables;


import codegen.Indexes;
import codegen.Keys;
import codegen.Public;
import codegen.tables.records.PenaltyRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Penalty extends TableImpl<PenaltyRecord> {

    private static final long serialVersionUID = 1969964744;

    /**
     * The reference instance of <code>public.penalty</code>
     */
    public static final Penalty PENALTY = new Penalty();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PenaltyRecord> getRecordType() {
        return PenaltyRecord.class;
    }

    /**
     * The column <code>public.penalty.id</code>.
     */
    public final TableField<PenaltyRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('penalty_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.penalty.percentage</code>.
     */
    public final TableField<PenaltyRecord, BigDecimal> PERCENTAGE = createField("percentage", org.jooq.impl.SQLDataType.NUMERIC.nullable(false), this, "");

    /**
     * The column <code>public.penalty.reference_value</code>.
     */
    public final TableField<PenaltyRecord, BigDecimal> REFERENCE_VALUE = createField("reference_value", org.jooq.impl.SQLDataType.NUMERIC.nullable(false), this, "");

    /**
     * The column <code>public.penalty.slo_id</code>.
     */
    public final TableField<PenaltyRecord, Integer> SLO_ID = createField("slo_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.penalty</code> table reference
     */
    public Penalty() {
        this(DSL.name("penalty"), null);
    }

    /**
     * Create an aliased <code>public.penalty</code> table reference
     */
    public Penalty(String alias) {
        this(DSL.name(alias), PENALTY);
    }

    /**
     * Create an aliased <code>public.penalty</code> table reference
     */
    public Penalty(Name alias) {
        this(alias, PENALTY);
    }

    private Penalty(Name alias, Table<PenaltyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Penalty(Name alias, Table<PenaltyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Penalty(Table<O> child, ForeignKey<O, PenaltyRecord> key) {
        super(child, key, PENALTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PENALTY_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PenaltyRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PENALTY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PenaltyRecord> getPrimaryKey() {
        return Keys.PENALTY_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PenaltyRecord>> getKeys() {
        return Arrays.<UniqueKey<PenaltyRecord>>asList(Keys.PENALTY_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<PenaltyRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PenaltyRecord, ?>>asList(Keys.PENALTY__PENALTY_SLO_ID_FKEY);
    }

    public ServiceLevelObjective serviceLevelObjective() {
        return new ServiceLevelObjective(this, Keys.PENALTY__PENALTY_SLO_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Penalty as(String alias) {
        return new Penalty(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Penalty as(Name alias) {
        return new Penalty(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Penalty rename(String name) {
        return new Penalty(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Penalty rename(Name name) {
        return new Penalty(name, null);
    }
}
