package ch.uzh.slamer;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ch.uzh.slamer.model.Tables.*;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class SlamerApplication {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SlamerApplication.class, args);
        String user = System.getProperty("jdbc.user");
        String password = System.getProperty("jdbc.password");
        String url = System.getProperty("jdbc.url");
        String driver = System.getProperty("jdbc.driver");

        Class.forName(driver).newInstance();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            Result<Record> result = dslContext.select().from(SLA_USER).fetch();

            for (Record r : result) {
                Integer id = r.getValue(SLA_USER.ID);
                String firstName = r.getValue(SLA_USER.PARTY_NAME);

                System.out.println("ID: " + id + " first name: " + firstName);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
