package com.abcbankinggroup.Services.Authentication.impl;

import com.abcbankinggroup.DTOs.Credentials;
import com.abcbankinggroup.DTOs.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.sql.DataSource;
import java.sql.SQLException;

public class AuthenticationServiceImplentationsTest {

    private final AuthenticationServiceImplentations cut = new AuthenticationServiceImplentations();

    private Credentials credentials;
    final static private String NAME = "some-name";
    final static private String USERNAME = "some-username";
    final static private String PASSWORD = "some-password";

    @Mock
    DataSource dataSourceMock;

    @Before
    public void setUp() throws Exception {

        cut.setDataSource(dataSourceMock);

        credentials = new Credentials();
        credentials.setPassword(PASSWORD);
        credentials.setUsername(USERNAME);
    }

    @Test
    public void verifyCredentialsTest() {

        try {
            User user = cut.verifyCredentials(credentials);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void returnUser() {

        try {
            cut.verifyCredentials(credentials);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}