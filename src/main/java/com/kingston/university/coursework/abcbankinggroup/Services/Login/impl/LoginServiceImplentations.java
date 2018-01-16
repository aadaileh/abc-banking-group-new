package com.kingston.university.coursework.abcbankinggroup.Services.Login.impl;

import com.kingston.university.coursework.abcbankinggroup.Connection.DatabaseConnectionSingleton;
import com.kingston.university.coursework.abcbankinggroup.Services.Login.LoginServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class LoginServiceImplentations {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    public String dbService()  throws SQLException {
        DatabaseConnectionSingleton databaseConnectionSingleton = DatabaseConnectionSingleton.getInstance();
        databaseConnectionSingleton.setDbUrl(dbUrl);
        DataSource dataSource = databaseConnectionSingleton.dataSource();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet resultSet = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (resultSet.next()) {
                output.add("Read from DB: " + resultSet.getTimestamp("tick"));
            }

            connection.close();

            return output.toString(); //"ok, added";

        } catch (Exception e) {

            LOG.debug(e.getMessage());
        }
        return "";
    }
}
