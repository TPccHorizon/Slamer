/*
 * This file is generated by jOOQ.
 */
package codegen.tables.records;


import codegen.tables.GanacheUrl;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class GanacheUrlRecord extends UpdatableRecordImpl<GanacheUrlRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = -287244547;

    /**
     * Setter for <code>public.ganache_url.id</code>.
     */
    public GanacheUrlRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.ganache_url.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.ganache_url.url</code>.
     */
    public GanacheUrlRecord setUrl(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.ganache_url.url</code>.
     */
    public String getUrl() {
        return (String) get(1);
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
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return GanacheUrl.GANACHE_URL.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return GanacheUrl.GANACHE_URL.URL;
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
    public String component2() {
        return getUrl();
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
    public String value2() {
        return getUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GanacheUrlRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GanacheUrlRecord value2(String value) {
        setUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GanacheUrlRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached GanacheUrlRecord
     */
    public GanacheUrlRecord() {
        super(GanacheUrl.GANACHE_URL);
    }

    /**
     * Create a detached, initialised GanacheUrlRecord
     */
    public GanacheUrlRecord(Integer id, String url) {
        super(GanacheUrl.GANACHE_URL);

        set(0, id);
        set(1, url);
    }
}
