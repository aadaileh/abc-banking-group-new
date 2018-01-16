package com.kingston.university.coursework.abcbankinggroup.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class DatabaseConnectionSingleton {

    private String dbUrl;

    private static DatabaseConnectionSingleton ourInstance = new DatabaseConnectionSingleton();

    public static DatabaseConnectionSingleton getInstance() {
        return ourInstance;
    }

    private DatabaseConnectionSingleton() {
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    //    @Bean
    public javax.sql.DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setUsername("b9579c6ae9cba0");
            config.setPassword("89a88141");
            return new HikariDataSource(config);
        }
    }
}
