package com.abcbankinggroup.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * <h1>Database Connection Singleton</h1>
 *
 * <p>
 * This is a singleton to deal with all database interactions. It demonstrate
 * the implementation of a singleton, which is existing only once in the heap.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
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
