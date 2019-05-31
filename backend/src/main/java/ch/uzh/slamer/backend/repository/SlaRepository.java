package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.SlaDTO;
import ch.uzh.slamer.backend.model.pojo.Report;
import ch.uzh.slamer.backend.model.pojo.SlaState;
import ch.uzh.slamer.backend.model.pojo.SlaWithCustomer;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaUser;
import codegen.tables.records.SlaRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static ch.uzh.slamer.backend.model.enums.LifecyclePhase.*;
import static ch.uzh.slamer.backend.model.enums.SlaStatus.IDENTIFIED;
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
    public Sla update(Sla sla) throws RecordNotFoundException {
        int updateRecordCount = context.update(SLA)
                .set(SLA.STATUS, sla.getStatus())
                .set(SLA.SERVICE_PRICE, sla.getServicePrice())
                .set(SLA.LIFECYCLE_PHASE, sla.getLifecyclePhase())
                .set(SLA.VALID_FROM, sla.getValidFrom())
                .set(SLA.VALID_TO, sla.getValidTo())
                .set(SLA.SERVICE_PROVIDER_ID, sla.getServiceProviderId())
                .set(SLA.SERVICE_CUSTOMER_ID, sla.getServiceCustomerId())
                .execute();
        return findById(sla.getId());
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

        List<SlaUser> parties = context.select(SLA_USER.ID, SLA_USER.USERNAME, SLA_USER.PARTY_NAME)
                .from(SLA_USER)
                .where(SLA_USER.ID.equal(sla.getServiceProviderId()))
                .or(SLA_USER.ID.equal(sla.getServiceCustomerId()))
                .fetchInto(SlaUser.class);

        Map<Sla, List<SlaUser>> result = new LinkedHashMap<>();
        result.put(sla, parties);
        return result;
    }

    @Transactional(readOnly = true)
    public Integer countNewSLAs(int id) {
        return context.selectCount().from(SLA)
                .where(SLA.STATUS.eq(IDENTIFIED.getStatus()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(id))
                .fetchOne(0, int.class);
    }

    @Transactional(readOnly = true)
    public List<Sla> getSlasForReview(int id) {
        return context.selectFrom(SLA)
                .where(SLA.STATUS.eq(IDENTIFIED.getStatus()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(id))
                .fetchInto(Sla.class);
    }

    @Transactional(readOnly = true)
    public Report getSlaReport(int userId) {
//        Field<String> definition = SLA.LIFECYCLE_PHASE.as("definition");
        int definition = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(DEFINITION.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int negotiation = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(NEGOTIATION.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int monitoring = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(MONITORING.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int management = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(MANAGEMENT.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));

        int termination = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(TERMINATION.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));
        int penaltyEnforcment = context.fetchCount(context.selectFrom(SLA)
                .where(SLA.LIFECYCLE_PHASE.eq(PENALTY_ENFORCEMENT.getPhase()))
                .and(SLA.SERVICE_CUSTOMER_ID.equal(userId).or(SLA.SERVICE_PROVIDER_ID.equal(userId))));

        return new Report(definition, negotiation, monitoring, management, termination, penaltyEnforcment);
    }
}
