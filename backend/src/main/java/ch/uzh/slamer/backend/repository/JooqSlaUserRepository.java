package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import codegen.tables.pojos.SlaUser;
import codegen.tables.records.SlaUserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static codegen.Tables.SLA_USER;

@Repository
public class JooqSlaUserRepository implements SlaUserRepository{

    private DSLContext jooq;

    @Autowired
    public JooqSlaUserRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    private SlaUser convertResultIntoModel(SlaUserRecord queryResult) {
        return queryResult.into(SlaUser.class);
    }

    private SlaUserRecord createRecord(SlaUser user) {
        SlaUserRecord record = new SlaUserRecord();
        record.setPartyName(user.getPartyName())
                .setPassword(user.getPassword())
                .setUsername(user.getUsername())
                .setSalt(user.getSalt())
                .setPhoneNr(user.getPhoneNr());
        return record;
    }

    @Transactional
    @Override
    public SlaUser add(SlaUser user) {
        SlaUserRecord persisted = jooq.insertInto(SLA_USER)
                .set(createRecord(user))
                .returning()
                .fetchOne();
        return convertResultIntoModel(persisted);
    }

    @Transactional
    @Override
    public SlaUser delete(int id) {
        SlaUser deleted = null;
        try {
            deleted = findById(id);
        } catch (SlaUserNotFoundException e) {
            e.printStackTrace();
        }

        int deletedRecordCount = jooq.delete(SLA_USER)
                .where(SLA_USER.ID.equal(id))
                .execute();

        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SlaUser> findAll() {
        List<SlaUser> users = new ArrayList<>();

        List<SlaUserRecord> queryResults = jooq.selectFrom(SLA_USER).fetchInto(SlaUserRecord.class);
        for (SlaUserRecord queryResult: queryResults) {
            SlaUser user = convertResultIntoModel(queryResult);
            users.add(user);
        }

        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public SlaUser findById(int id) throws SlaUserNotFoundException {
        SlaUserRecord queryResult = jooq.selectFrom(SLA_USER)
                .where(SLA_USER.ID.equal(id))
                .fetchOne();

        if (queryResult == null) {
            throw new SlaUserNotFoundException("No User was found with id: " + id);
        }

        return convertResultIntoModel(queryResult);
    }

    @Transactional
    @Override
    public SlaUser update(SlaUser slaUser) throws SlaUserNotFoundException {
        int updatedRecordCount = jooq.update(SLA_USER)
                .set(SLA_USER.PARTY_NAME, slaUser.getPartyName())
                .set(SLA_USER.PARTY_TYPE, slaUser.getPartyType())
                .set(SLA_USER.PASSWORD, slaUser.getPassword())
                .set(SLA_USER.SALT, slaUser.getSalt())
                .set(SLA_USER.PHONE_NR, slaUser.getPhoneNr())
                .set(SLA_USER.USERNAME, slaUser.getUsername())
                .where(SLA_USER.ID.equal(slaUser.getId()))
                .execute();

        return findById(slaUser.getId());
    }

    @Override
    public SlaUser findByUsername(String username) throws SlaUserNotFoundException {
        SlaUserRecord record = jooq.selectFrom(SLA_USER).where(SLA_USER.USERNAME.equal(username)).fetchOne();
        if (record == null) {
            throw new SlaUserNotFoundException("No User found with username: " + username);
        }
        return convertResultIntoModel(record);
    }
}
