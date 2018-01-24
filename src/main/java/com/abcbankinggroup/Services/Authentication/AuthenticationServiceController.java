package com.abcbankinggroup.Services.Authentication;

import com.abcbankinggroup.DTOs.Credentials;
import com.abcbankinggroup.DTOs.User;
import com.abcbankinggroup.Services.Authentication.impl.AuthenticationServiceImplentations;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * <h1>Authentication Service</h1>
 *
 * <p>
 * Main Controller for the Authentication-Service. It implements all needed
 * methods for the mentioned service, to verify and authenticate requests and clients.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@RestController
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@CrossOrigin(origins = "nn*", maxAge = 3600)
@EnableSwagger2
public class AuthenticationServiceController extends AuthenticationServiceImplentations implements AuthenticationServiceInterface {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceController.class);

    @Autowired
    private AuthenticationServiceImplentations authenticationServiceImplentations;

    /**
     * Method to verify the given credentials. Credentials can be either coming from ATM (card-id, pin) or
     * Online banking (username, password). This method makes a use of the login-service via Feign client.
     * It returns either the logged in user's data, in case of success. Or FALSE, in case of failure.
     *
     * @param credentials contains user's credentials
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Authenticate system users by verifying their login credentials")
    @RequestMapping(value = "/api/login-service/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public User verifyLogin(@RequestBody Credentials credentials) throws SQLException {

        return authenticationServiceImplentations.verifyCredentials(credentials);
    }

    /**
     * Return the logged in user's data upon need. It returns either the requested user's data,
     * in case it is found, Or empty object, in case of failure.
     *
     * @param username user's username
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Returns the logged in user's data")
    @RequestMapping(value = "/api/login-service/users/{username}",
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public User getUser(@PathVariable String username) throws SQLException {

        return authenticationServiceImplentations.returnUser(username);
    }

    @ExceptionHandler
    private void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

    /** ONLY DONE FOR UNIT-TESTING PURPOSES.*/
    public void setAuthenticationServiceImplentations(AuthenticationServiceImplentations authenticationServiceImplentations) {
        this.authenticationServiceImplentations = authenticationServiceImplentations;
    }
}