package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import org.jooq.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AbstractRepository<R extends UpdatableRecord, ID, T> implements JooqRepository<T> {

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

    @Override
    public T delete(int id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Transactional
    public T findById(ID id) throws SlaUserNotFoundException {
        R queryResult = context.selectFrom(table)
                .where(table.field(idField).equal(id))
                .fetchOne();
        if (queryResult == null) {
            throw new SlaUserNotFoundException("NOT FOUND");
        }

        return convertResultIntoModel(queryResult);
    }

    @Override
    public T findByUsername(String username) throws SlaUserNotFoundException {
        return null;
    }

    @Override
    public T update(T user) throws SlaUserNotFoundException {
        return null;
    }

    private T convertResultIntoModel(R result) {
        return result.into(pojoClass);
    }
}
