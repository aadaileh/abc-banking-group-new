package com.abcbankinggroup.Services.Login.impl;

import com.abcbankinggroup.Connection.DataSourceAbstract;
import com.abcbankinggroup.DTOs.Credentials;
import com.abcbankinggroup.DTOs.User;
import com.abcbankinggroup.Services.Login.LoginServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class LoginServiceImplentations extends DataSourceAbstract {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceController.class);

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

        dataSource = getDataSource();
        Connection connection = null;
        User user = new User();

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
                user.setClientId(resultSet.getInt("id"));
            }

            //Set the timestamp user-last-logged-in when successfully logged in
            if (user.isLoggedIn()){
                int updatedRowId = updateRecord(stmt, user.getClientId());
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
     * Method to return the logged in user's data upon need. It returns either the requested user's data,
     * in case it is found, Or empty object, in case of failure.
     *
     * @param username contains user's username
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public User returnUser(String username) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        User user = new User();

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * " +
                            "FROM client " +
                            "WHERE username_card_id = '" + username + "'");

            while (resultSet.next()) {
                user.setClientId(resultSet.getInt("id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setAddress(resultSet.getString("user_address"));
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
}
