package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import codegen.tables.pojos.Penalty;
import codegen.tables.records.PenaltyRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static codegen.tables.Penalty.PENALTY;

@Repository
public class PenaltyRepository extends AbstractRepository<PenaltyRecord, Integer, Penalty>{

    public PenaltyRepository(DSLContext context) {
        super(context, PENALTY, PENALTY.ID, Penalty.class);
    }

    @Override
    public Penalty update(Penalty entity) throws RecordNotFoundException {
        return null;
    }
}
