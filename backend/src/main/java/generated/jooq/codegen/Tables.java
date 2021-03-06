/*
 * This file is generated by jOOQ.
 */
package codegen;


import codegen.tables.Databasechangelog;
import codegen.tables.Databasechangeloglock;
import codegen.tables.GanacheUrl;
import codegen.tables.ServiceLevelObjective;
import codegen.tables.Sla;
import codegen.tables.SlaReview;
import codegen.tables.SlaUser;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.databasechangelog</code>.
     */
    public static final Databasechangelog DATABASECHANGELOG = codegen.tables.Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>public.databasechangeloglock</code>.
     */
    public static final Databasechangeloglock DATABASECHANGELOGLOCK = codegen.tables.Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * The table <code>public.ganache_url</code>.
     */
    public static final GanacheUrl GANACHE_URL = codegen.tables.GanacheUrl.GANACHE_URL;

    /**
     * The table <code>public.service_level_objective</code>.
     */
    public static final ServiceLevelObjective SERVICE_LEVEL_OBJECTIVE = codegen.tables.ServiceLevelObjective.SERVICE_LEVEL_OBJECTIVE;

    /**
     * The table <code>public.sla</code>.
     */
    public static final Sla SLA = codegen.tables.Sla.SLA;

    /**
     * The table <code>public.sla_review</code>.
     */
    public static final SlaReview SLA_REVIEW = codegen.tables.SlaReview.SLA_REVIEW;

    /**
     * The table <code>public.sla_user</code>.
     */
    public static final SlaUser SLA_USER = codegen.tables.SlaUser.SLA_USER;
}
