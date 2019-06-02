package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import codegen.tables.pojos.ServiceLevelObjective;
import codegen.tables.records.ServiceLevelObjectiveRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static codegen.tables.ServiceLevelObjective.SERVICE_LEVEL_OBJECTIVE;
import static codegen.tables.SloType.SLO_TYPE;

@Repository
public class ServiceLevelObjectiveRepository extends AbstractRepository<ServiceLevelObjectiveRecord, Integer, ServiceLevelObjective>{

    public ServiceLevelObjectiveRepository(DSLContext context) {
        super(context, SERVICE_LEVEL_OBJECTIVE, SERVICE_LEVEL_OBJECTIVE.ID, ServiceLevelObjective.class);
    }


    @Override
    public ServiceLevelObjective update(ServiceLevelObjective entity){
        ServiceLevelObjectiveRecord record = context.newRecord(SERVICE_LEVEL_OBJECTIVE, entity);
        record.update();
        return record.into(ServiceLevelObjective.class);
    }

    @Transactional
    @Override
    public ServiceLevelObjective add(ServiceLevelObjective slo) {
        ServiceLevelObjectiveRecord persisted = context.insertInto(SERVICE_LEVEL_OBJECTIVE)
                .set(createRecord(slo))
                .returning().fetchOne();
        return convertResultIntoModel(persisted);
    }

    @Transactional
    public List<ServiceLevelObjective> getAllBySlaId(int slaId) {
        return context.selectFrom(SERVICE_LEVEL_OBJECTIVE).where(SERVICE_LEVEL_OBJECTIVE.SLA_ID.equal(slaId))
                .fetchInto(ServiceLevelObjective.class);
    }
}
