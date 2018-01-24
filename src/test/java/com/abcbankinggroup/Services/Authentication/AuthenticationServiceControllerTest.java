package com.abcbankinggroup.Services.Authentication;

import com.abcbankinggroup.DTOs.Credentials;
import com.abcbankinggroup.DTOs.User;
import com.abcbankinggroup.Services.Authentication.impl.AuthenticationServiceImplentations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceControllerTest {

    private final AuthenticationServiceController cut = new AuthenticationServiceController();

    private Credentials credentials;
    final static private String NAME = "some-name";
    final static private String USERNAME = "some-username";
    final static private String PASSWORD = "some-password";

    @Mock
    private AuthenticationServiceImplentations authenticationServiceImplentationsMock;

    @Before
    public void setUp() throws Exception {

        credentials = new Credentials();
        credentials.setPassword(PASSWORD);
        credentials.setUsername(USERNAME);

        User user = new User();
        user.setName(NAME);

        cut.setAuthenticationServiceImplentations(authenticationServiceImplentationsMock);
        when(authenticationServiceImplentationsMock.verifyCredentials(anyObject())).thenReturn(user);
        when(authenticationServiceImplentationsMock.returnUser(anyString())).thenReturn(user);

    }

    @Test
    public void verifyLoginTest() throws SQLException {

        User user = cut.verifyLogin(credentials);

        assertTrue(user.getName().equals(NAME));
    }

    @Test
    public void getUserTest() throws SQLException {
        User user = cut.getUser(anyString());

        assertTrue(user.getName().equals(NAME));
    }
}