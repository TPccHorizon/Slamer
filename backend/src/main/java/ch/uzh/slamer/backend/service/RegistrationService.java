package ch.uzh.slamer.backend.service;

import codegen.tables.pojos.SlaUser;
import codegen.tables.records.SlaUserRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

import static codegen.Tables.SLA_USER;

@Component
public class RegistrationService {

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.url}")
    String url;

    public SlaUser registerUser(SlaUser user) {
        SlaUserRecord slaUserRecord = new SlaUserRecord();
        try (Connection conn = DriverManager.getConnection(url, userName, password)){
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            slaUserRecord = create.newRecord(SLA_USER);
            slaUserRecord.setPartyName(user.getPartyName())
                .setPartyType(user.getPartyType())
                .setPassword(user.getPassword())
                .setUsername(user.getUsername())
                .setSalt(user.getSalt())
                .setPhoneNr(user.getPhoneNr());
            slaUserRecord.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return slaUserRecord.into(SlaUser.class);
    }
}
