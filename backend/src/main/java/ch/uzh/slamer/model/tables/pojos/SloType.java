/*
 * This file is generated by jOOQ.
 */
package ch.uzh.slamer.model.tables.pojos;


import java.io.Serializable;

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
public class SloType implements Serializable {

    private static final long serialVersionUID = -1688811546;

    private Integer id;
    private String  type;

    public SloType() {}

    public SloType(SloType value) {
        this.id = value.id;
        this.type = value.type;
    }

    public SloType(
        Integer id,
        String  type
    ) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SloType (");

        sb.append(id);
        sb.append(", ").append(type);

        sb.append(")");
        return sb.toString();
    }
}
