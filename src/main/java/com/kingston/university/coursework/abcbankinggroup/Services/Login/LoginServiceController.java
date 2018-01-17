package com.kingston.university.coursework.abcbankinggroup.Services.Login;

import com.kingston.university.coursework.abcbankinggroup.DTOs.Credentials;
import com.kingston.university.coursework.abcbankinggroup.DTOs.User;
import com.kingston.university.coursework.abcbankinggroup.Services.Login.impl.LoginServiceImplentations;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

@RestController
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableSwagger2
public class LoginServiceController extends LoginServiceImplentations implements LoginServiceInterface {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoginServiceImplentations loginServiceImplentations;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://warm-harbor-89034.herokuapp.com").build();
    }

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

        return loginServiceImplentations.verifyCredentials(credentials);
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
