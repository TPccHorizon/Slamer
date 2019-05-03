package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import codegen.tables.pojos.SlaUser;

import java.util.List;

public interface SlaUserRepository {
    SlaUser add(SlaUser user);

    SlaUser delete(int id);

    List<SlaUser> findAll();

    SlaUser findById(int id) throws SlaUserNotFoundException;

    SlaUser findByUsername(String username) throws SlaUserNotFoundException;

    SlaUser update(SlaUser user) throws SlaUserNotFoundException;

}
