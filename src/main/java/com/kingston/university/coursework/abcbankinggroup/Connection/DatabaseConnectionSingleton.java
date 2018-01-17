package com.kingston.university.coursework.abcbankinggroup.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class DatabaseConnectionSingleton {

    private static DatabaseConnectionSingleton ourInstance = new DatabaseConnectionSingleton();
    private String dbUrl;
    private String username;
    private String password;

    private DatabaseConnectionSingleton() {
    }

    public static DatabaseConnectionSingleton getInstance() {
        return ourInstance;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    @Bean
    public javax.sql.DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setUsername(username);
            config.setPassword(password);
            return new HikariDataSource(config);
        }
    }
}
