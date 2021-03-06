/*
 * This file is generated by jOOQ.
 */
package codegen.tables;


import codegen.Indexes;
import codegen.Keys;
import codegen.Public;
import codegen.tables.records.ServiceLevelObjectiveRecord;

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
public class ServiceLevelObjective extends TableImpl<ServiceLevelObjectiveRecord> {

    private static final long serialVersionUID = 1937815558;

    /**
     * The reference instance of <code>public.service_level_objective</code>
     */
    public static final ServiceLevelObjective SERVICE_LEVEL_OBJECTIVE = new ServiceLevelObjective();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ServiceLevelObjectiveRecord> getRecordType() {
        return ServiceLevelObjectiveRecord.class;
    }

    /**
     * The column <code>public.service_level_objective.id</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('service_level_objective_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.service_level_objective.name</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.service_level_objective.sla_id</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, Integer> SLA_ID = createField("sla_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.service_level_objective.time_unit</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, String> TIME_UNIT = createField("time_unit", org.jooq.impl.SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>public.service_level_objective.relational_operator</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, String> RELATIONAL_OPERATOR = createField("relational_operator", org.jooq.impl.SQLDataType.CHAR(2), this, "");

    /**
     * The column <code>public.service_level_objective.slo_type</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, String> SLO_TYPE = createField("slo_type", org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.service_level_objective.percentage_of_availability</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, BigDecimal> PERCENTAGE_OF_AVAILABILITY = createField("percentage_of_availability", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>public.service_level_objective.throughput_data_unit</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, String> THROUGHPUT_DATA_UNIT = createField("throughput_data_unit", org.jooq.impl.SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>public.service_level_objective.throughput_data_size</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, BigDecimal> THROUGHPUT_DATA_SIZE = createField("throughput_data_size", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>public.service_level_objective.throughput_threshold_value</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, BigDecimal> THROUGHPUT_THRESHOLD_VALUE = createField("throughput_threshold_value", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>public.service_level_objective.average_response_time_value</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, BigDecimal> AVERAGE_RESPONSE_TIME_VALUE = createField("average_response_time_value", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>public.service_level_objective.accepted</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, Boolean> ACCEPTED = createField("accepted", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.service_level_objective.comment</code>.
     */
    public final TableField<ServiceLevelObjectiveRecord, String> COMMENT = createField("comment", org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * Create a <code>public.service_level_objective</code> table reference
     */
    public ServiceLevelObjective() {
        this(DSL.name("service_level_objective"), null);
    }

    /**
     * Create an aliased <code>public.service_level_objective</code> table reference
     */
    public ServiceLevelObjective(String alias) {
        this(DSL.name(alias), SERVICE_LEVEL_OBJECTIVE);
    }

    /**
     * Create an aliased <code>public.service_level_objective</code> table reference
     */
    public ServiceLevelObjective(Name alias) {
        this(alias, SERVICE_LEVEL_OBJECTIVE);
    }

    private ServiceLevelObjective(Name alias, Table<ServiceLevelObjectiveRecord> aliased) {
        this(alias, aliased, null);
    }

    private ServiceLevelObjective(Name alias, Table<ServiceLevelObjectiveRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ServiceLevelObjective(Table<O> child, ForeignKey<O, ServiceLevelObjectiveRecord> key) {
        super(child, key, SERVICE_LEVEL_OBJECTIVE);
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
        return Arrays.<Index>asList(Indexes.SERVICE_LEVEL_OBJECTIVE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ServiceLevelObjectiveRecord, Integer> getIdentity() {
        return Keys.IDENTITY_SERVICE_LEVEL_OBJECTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ServiceLevelObjectiveRecord> getPrimaryKey() {
        return Keys.SERVICE_LEVEL_OBJECTIVE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ServiceLevelObjectiveRecord>> getKeys() {
        return Arrays.<UniqueKey<ServiceLevelObjectiveRecord>>asList(Keys.SERVICE_LEVEL_OBJECTIVE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ServiceLevelObjectiveRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ServiceLevelObjectiveRecord, ?>>asList(Keys.SERVICE_LEVEL_OBJECTIVE__SERVICE_LEVEL_OBJECTIVE_SLA_ID_FKEY);
    }

    public Sla sla() {
        return new Sla(this, Keys.SERVICE_LEVEL_OBJECTIVE__SERVICE_LEVEL_OBJECTIVE_SLA_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceLevelObjective as(String alias) {
        return new ServiceLevelObjective(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceLevelObjective as(Name alias) {
        return new ServiceLevelObjective(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ServiceLevelObjective rename(String name) {
        return new ServiceLevelObjective(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ServiceLevelObjective rename(Name name) {
        return new ServiceLevelObjective(name, null);
    }
}
