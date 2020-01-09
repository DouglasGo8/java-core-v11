package com.packtpub.jdk.core.eleven.chapter06.hirakicp;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.lang.System.out;

public class App {


    private DataSource getDs() {

        HikariDataSource ds = new HikariDataSource();

        ds.setPoolName("cookbookPool");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl("jdbc:postgresql://localhost/postgresdb");
        ds.setUsername("guest");
        ds.setPassword("welcome1");
        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(2);
        ds.addDataSourceProperty("cachePreStmts", Boolean.TRUE);
        ds.addDataSourceProperty("preStmtCacheSize", 256);
        ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
        ds.addDataSourceProperty("useServerPrepStmts", Boolean.TRUE);

        return ds;
    }


    @Test
    public void readData() {
        try (Connection conn = this.getDs().getConnection()) {
            try(PreparedStatement pstmt = conn.prepareStatement("SELECT model, price FROM Car")) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    out.println(rs.getString("model"));
                    out.println(rs.getDouble("price"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
