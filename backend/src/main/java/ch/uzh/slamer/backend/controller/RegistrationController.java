package ch.uzh.slamer.backend.controller;


import ch.uzh.slamer.backend.model.pojo.SlaUser;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
public class RegistrationController {

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.url}")
    String url;

    @RequestMapping(method = RequestMethod.GET, path = "/register")
    public SlaUser register() {
        SlaUser user = new SlaUser();
        try (Connection conn = DriverManager.getConnection(url, userName, password)){
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            user = create.select().from("SLA_USER").fetchOne().into(SlaUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
