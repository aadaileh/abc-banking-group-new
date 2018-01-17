package com.kingston.university.coursework.abcbankinggroup.Services.Login.impl;

import com.kingston.university.coursework.abcbankinggroup.Connection.DatabaseConnectionSingleton;
import com.kingston.university.coursework.abcbankinggroup.DTOs.Credentials;
import com.kingston.university.coursework.abcbankinggroup.DTOs.User;
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

@Service
public class LoginServiceImplentations {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private DataSource dataSource;

    /**
     * Method verifies the given credentials (username/card-id and password/pin)
     * it returns the user object on success and empty user objecton failure.
     *
     * @param credentials username/card-id and password/pin
     * @return user
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public User verifyCredentials(Credentials credentials) throws SQLException {

        DataSource dataSource = getDataSource();
        Connection connection = null;
        User user = new User();
        int userId = 0;

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * " +
                            "FROM client " +
                            "WHERE username_card_id = '" + credentials.getUsername() + "' " +
                            "AND password_pin = '" + credentials.getPassword() + "'");

            user.setLoggedIn(false);

            while (resultSet.next()) {
                user.setLoggedIn(true);
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setAddress(resultSet.getString("user_address"));

                //get the row id to use it for updating the record's timestamp later on
                userId = resultSet.getInt("id");
            }

            //Set the timestamp user-last-logged-in when successfully logged in
            if (user.isLoggedIn()){
                int updatedRowId = updateRecord(stmt, userId);
            }

            return user;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            connection.close();
        }

        return user;
    }

    /**
     * Set the timestamp user-last-logged-in when successfully logged in
     * @param stmt SQL Stmt to update timestamp
     * @param id Row id
     *
     * @return updated row id
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    private int updateRecord(Statement stmt, int id) throws SQLException {
        String updateRowWithTimestamp = "UPDATE client " +
                "SET last_logged_in = NOW() " +
                "WHERE id = " + id;
        return stmt.executeUpdate(
                updateRowWithTimestamp);
    }

    /**
     * Build connection to database
     * @return Datasource to the database
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    private DataSource getDataSource() throws SQLException {
        DatabaseConnectionSingleton databaseConnectionSingleton = DatabaseConnectionSingleton.getInstance();
        databaseConnectionSingleton.setDbUrl(dbUrl);
        databaseConnectionSingleton.setUsername(username);
        databaseConnectionSingleton.setPassword(password);
        return databaseConnectionSingleton.dataSource();
    }
}
