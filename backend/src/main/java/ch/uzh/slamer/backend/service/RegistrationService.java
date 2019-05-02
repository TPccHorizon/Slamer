package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.model.pojo.SlaUser;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class RegistrationService {

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.url}")
    String url;

    public SlaUser registerUser(SlaUser user) {
        try (Connection conn = DriverManager.getConnection(url, userName, password)){
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            user = create.select().from("SLA_USER").fetchOne().into(SlaUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
