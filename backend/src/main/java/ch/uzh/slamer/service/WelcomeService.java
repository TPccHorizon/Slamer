package ch.uzh.slamer.service;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class WelcomeService {
    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.password}")
    String password;

    public String getWelcomeMessage() {


        String message;

        try (Connection conn = DriverManager.getConnection(url, userName, password)){
            System.out.println("Connected");
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            Record result = create.select().from("SLA_USER").fetchOne();

            Integer id = (Integer) result.getValue("id");
            String firstName = (String) result.getValue("username");

            message = "ID: " + id + " first name: " + firstName;
        } catch (Exception e) {
            message = "Failed";
            e.printStackTrace();
        }

        return message;
    }
}
