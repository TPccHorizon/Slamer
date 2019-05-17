package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;

import java.util.List;

public interface JooqRepository<T, ID> {
    T add(T user);

    T delete(ID id);

    List<T> findAll();

    T findById(ID id) throws RecordNotFoundException;

    T update(T user) throws RecordNotFoundException;

}
