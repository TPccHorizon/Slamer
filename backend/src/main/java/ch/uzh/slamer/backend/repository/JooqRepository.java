package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import codegen.tables.pojos.SlaUser;

import java.util.List;

public interface JooqRepository<T> {
    T add(T user);

    T delete(int id);

    List<T> findAll();

    T findByUsername(String username) throws SlaUserNotFoundException;

    T update(T user) throws SlaUserNotFoundException;

}
