package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
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
public class SlaUserRepository extends AbstractRepository<SlaUserRecord, Integer, SlaUser> {


    @Autowired
    public SlaUserRepository(DSLContext context) {
        super(context, SLA_USER, SLA_USER.ID, SlaUser.class);
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
        SlaUserRecord persisted = context.insertInto(SLA_USER)
                .set(createRecord(user))
                .returning()
                .fetchOne();
        return convertResultIntoModel(persisted);
    }

    @Transactional
    @Override
    public SlaUser update(SlaUser slaUser) throws RecordNotFoundException {
        int updatedRecordCount = context.update(SLA_USER)
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

    public SlaUser findByUsername(String username) throws RecordNotFoundException {
        SlaUserRecord record = context.selectFrom(SLA_USER).where(SLA_USER.USERNAME.equal(username)).fetchOne();
        if (record == null) {
            throw new RecordNotFoundException("No User found with username: " + username);
        }
        return convertResultIntoModel(record);
    }
}
