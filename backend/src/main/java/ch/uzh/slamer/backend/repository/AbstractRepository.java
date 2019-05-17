package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import org.jooq.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractRepository<R extends UpdatableRecord, ID, T> implements JooqRepository<T, ID> {

    protected final DSLContext context;
    private final Table<R> table;
    private final TableField<R, ID> idField;
    private final Class<T> pojoClass;

    public AbstractRepository(DSLContext context, Table<R> table, TableField<R, ID> idField, Class<T> pojoClass) {
        this.context = context;
        this.table = table;
        this.idField = idField;
        this.pojoClass = pojoClass;
    }

    @Override
    public T add(T user) {
        return null;
    }

    @Transactional
    @Override
    public T delete(ID id) {
        T deleted = null;
        try {
            deleted = findById(id);
        } catch (RecordNotFoundException e) {
            System.out.println(e.getMessage());
        }

        int deletedRecordCount = context.delete(table)
                .where(table.field(idField).equal(id))
                .execute();
        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return context.selectFrom(table).fetchInto(pojoClass);
    }

    @Transactional(readOnly = true)
    public T findById(ID id) throws RecordNotFoundException {
        R queryResult = context.selectFrom(table)
                .where(table.field(idField).equal(id))
                .fetchOne();
        if (queryResult == null) {
            throw new RecordNotFoundException("NOT FOUND");
        }

        return convertResultIntoModel(queryResult);
    }

    @Override
    public abstract T update(T user) throws RecordNotFoundException;

    private T convertResultIntoModel(R result) {
        return result.into(pojoClass);
    }
}
