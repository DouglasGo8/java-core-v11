package com.packtpub.jdk.core.eleven.chapter06.jdbc;

import org.junit.Ignore;
import org.junit.Test;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.System.out;

public class App {


    @Test
    @Ignore
    public void setUp() {
        try (Connection cnn = this.getDbConnection()) {

            out.println(cnn);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void basicSelect() {

        try (Connection cnn = this.getDbConnection()) {
            assert cnn != null;
            try (Statement stmt = cnn.createStatement()) {

                ResultSet rs = stmt.executeQuery("SELECT model, price FROM CAR");

                while (rs.next()) {
                    out.println(rs.getString("model"));
                    out.println(rs.getDouble("price"));
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private Connection getDbConnection() {
        try {

            PGPoolingDataSource source = new PGPoolingDataSource();

            source.setServerName("localhost");
            source.setDatabaseName("javacoredb");
            source.setInitialConnections(1);
            source.setUser("guest");
            source.setPassword("welcome1");
            source.setMaxConnections(5);
            source.setLoginTimeout(10);

            return source.getConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
