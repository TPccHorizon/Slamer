package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.pojo.Report;
import ch.uzh.slamer.backend.model.pojo.SlaForMonitoring;
import ch.uzh.slamer.backend.model.pojo.SlaState;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import codegen.tables.records.SlaRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static ch.uzh.slamer.backend.model.enums.LifecyclePhase.*;
import static ch.uzh.slamer.backend.model.enums.SlaStatus.*;
import static codegen.Tables.SLA;
import static codegen.Tables.SLA_USER;

@Repository
public class SlaRepository extends AbstractRepository<SlaRecord, Integer, Sla> {


    public SlaRepository(DSLContext context) {
        super(context, SLA, SLA.ID, Sla.class);
    }

    public List<Sla> getUsersSlas(int userId) {
        /* Do not return SLAs with status 'Identified' for customers */
        return context.selectFrom(SLA).where(SLA.SERVICE_CUSTOMER_ID.equal(userId)).and(SLA.STATUS.notEqual(IDENTIFIED.getStatus()))
                .or(SLA.SERVICE_PROVIDER_ID.equal(userId))
                .fetchInto(Sla.class);
    }

    @Override
    public Sla update(Sla sla) {
        SlaRecord record = context.newRecord(SLA, sla);
        record.update();
        return record.into(Sla.class);
    }

    public void updateState(int id, SlaState state) {
        int updateRecordCount = context.update(SLA)
                .set(SLA.STATUS, state.getStatus())
                .set(SLA.LIFECYCLE_PHASE, state.getPhase())
                .where(SLA.ID.eq(id))
                .execute();
    }

    @Override
    public SlaRecord createRecord(Sla sla) {
        return context.newRecord(SLA, sla);
    }

    public Map<Sla, List<SlaUser>> getSlaWithParties(int slaId) {
        Sla sla = context.selectFrom(SLA)
                .where(SLA.ID.equal(slaId))
                .fetchOne().into(Sla.class);

        List<SlaUser> parties = context.select(SLA_USER.ID, SLA_USER.USERNAME, SLA_USER.PARTY_NAME, SLA_USER.WALLET)
                .from(SLA_USER)
                .where(SLA_USER.ID.equal(sla.getServiceProviderId()))
                .or(SLA_USER.ID.equal(sla.getServiceCustomerId()))
                .or(SLA_USER.ID.equal(sla.getMonitoringSolutionId()))
                .fetchInto(SlaUser.class);

        Map<Sla, List<SlaUser>> result = new LinkedHashMap<>();
        result.put(sla, parties);
        return result;
    }

    public SlaForMonitoring getSlaForMonitoring(int slaId) throws RecordNotFoundException {
        Sla sla = findById(slaId);

        SlaUser monitoringSolution = context.select().from(SLA_USER)
                .where(SLA_USER.ID.eq(sla.getMonitoringSolutionId()))
                .fetchOne().into(SlaUser.class);

        return new SlaForMonitoring(sla, monitoringSolution);
    }

    @Transactional(readOnly = true)
    public Integer countNewSLAs(int id) {
        Condition forCustomer = SLA.STATUS.eq(REQUESTED.getStatus()).and(SLA.SERVICE_CUSTOMER_ID.equal(id));
        Condition forProvider = SLA.STATUS.eq(ACCEPTED.getStatus()).and(SLA.SERVICE_PROVIDER_ID.equal(id))
                .or(SLA.STATUS.equal(REJECTED.getStatus())).and(SLA.SERVICE_PROVIDER_ID.equal(id));

        return context.selectCount().from(SLA)
                .where(forCustomer)
                .or(forProvider)
                .fetchOne(0, int.class);
    }

    @Transactional(readOnly = true)
    public List<Sla> getSlasForReview(int id) {
        Condition forBoth = SLA.STATUS.eq(PENDING_DEPLOYMENT.getStatus()).or(SLA.STATUS.eq(PENDING_DEPOSIT.getStatus()));
        Condition forCustomer = SLA.STATUS.eq(REQUESTED.getStatus()).and(SLA.SERVICE_CUSTOMER_ID.equal(id))
                .or(SLA.STATUS.equal(DEPLOYMENT.getStatus())).and(SLA.SERVICE_CUSTOMER_ID.equal(id));
        Condition forProvider = SLA.STATUS.eq(ACCEPTED.getStatus()).and(SLA.SERVICE_PROVIDER_ID.equal(id))
                .or(SLA.STATUS.equal(REJECTED.getStatus())).and(SLA.SERVICE_PROVIDER_ID.equal(id));

        return context.selectFrom(SLA)
                // get slas for the customer to review
                .where(forCustomer)
                // get slas for the service provider to deploy/revise
                .or(forProvider)
                .or(forBoth)
                .fetchInto(Sla.class);
    }

    @Transactional(readOnly = true)
    public Report getSlaReport(int userId) {
//        Field<String> definition = SLA.LIFECYCLE_PHASE.as("definition");
        int definition = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(DEFINITION.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int establishment = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(ESTABLISHMENT.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int monitoring = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(MONITORING.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int termination = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(TERMINATION.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int penaltyEnforcment = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(PENALTY_ENFORCEMENT.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));

        return new Report(definition, establishment, monitoring, termination, penaltyEnforcment);
    }
}
