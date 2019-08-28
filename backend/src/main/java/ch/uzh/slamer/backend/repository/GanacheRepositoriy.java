package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import codegen.tables.pojos.GanacheUrl;
import codegen.tables.records.GanacheUrlRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static codegen.Tables.GANACHE_URL;

@Repository
public class GanacheRepositoriy extends AbstractRepository<GanacheUrlRecord, Integer, GanacheUrl> {

    public GanacheRepositoriy(DSLContext context) {
        super(context, GANACHE_URL, GANACHE_URL.ID, GanacheUrl.class);
    }

    @Override
    public GanacheUrl update(GanacheUrl entity) throws RecordNotFoundException {
        GanacheUrlRecord record = context.newRecord(GANACHE_URL, entity);
        record.update();
        return record.into(GanacheUrl.class);
    }

    @Transactional
    public GanacheUrl getFirst() {
        GanacheUrlRecord record = context.selectFrom(GANACHE_URL)
                .where(GANACHE_URL.ID.greaterOrEqual(0))
                .fetchOne();
        if (record != null) {
            return record.into(GanacheUrl.class);
        }
        return null;
    }
}
