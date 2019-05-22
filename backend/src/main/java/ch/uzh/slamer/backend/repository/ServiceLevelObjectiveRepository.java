package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import codegen.tables.pojos.ServiceLevelObjective;
import codegen.tables.records.ServiceLevelObjectiveRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static codegen.tables.ServiceLevelObjective.SERVICE_LEVEL_OBJECTIVE;
import static codegen.tables.SloType.SLO_TYPE;

@Repository
public class ServiceLevelObjectiveRepository extends AbstractRepository<ServiceLevelObjectiveRecord, Integer, ServiceLevelObjective>{

    public ServiceLevelObjectiveRepository(DSLContext context) {
        super(context, SERVICE_LEVEL_OBJECTIVE, SERVICE_LEVEL_OBJECTIVE.ID, ServiceLevelObjective.class);
    }


    @Override
    public ServiceLevelObjective update(ServiceLevelObjective entity) throws RecordNotFoundException {
        return null;
    }

    @Override
    public ServiceLevelObjectiveRecord createRecord(ServiceLevelObjective entity) {
        return context.newRecord(SERVICE_LEVEL_OBJECTIVE, entity);
    }

    @Transactional
    @Override
    public ServiceLevelObjective add(ServiceLevelObjective slo) {
        Integer sloTypeId = context.select(SLO_TYPE.ID).from(SLO_TYPE).limit(1).fetchOne().into(Integer.class);
        slo.setSloTypeId(sloTypeId);
        ServiceLevelObjectiveRecord persisted = context.insertInto(SERVICE_LEVEL_OBJECTIVE)
                .set(createRecord(slo))
                .returning().fetchOne();
        return convertResultIntoModel(persisted);
    }

    @Transactional(readOnly = true)
    public int getSloTypeId(String sloType) {
        System.out.println("SLO type: " + sloType);
        return context.select(SLO_TYPE.ID)
                .from(SLO_TYPE).where(SLO_TYPE.TYPE.eq(sloType))
                .fetchOne().into(Integer.class);
    }
}
