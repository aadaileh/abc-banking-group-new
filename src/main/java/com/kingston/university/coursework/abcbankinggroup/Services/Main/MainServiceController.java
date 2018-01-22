package com.kingston.university.coursework.abcbankinggroup.Services.Main;

import com.kingston.university.coursework.abcbankinggroup.Clients.FeignClient;
import com.kingston.university.coursework.abcbankinggroup.DTOs.Account;
import com.kingston.university.coursework.abcbankinggroup.DTOs.Credentials;
import com.kingston.university.coursework.abcbankinggroup.DTOs.User;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableSwagger2
public class MainServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceController.class);

    @Value("${spring.feign.url}")
    private String feignUrl;

    @Value("${spring.basicauthentication.username}")
    private String basicAuthenticationUsername;

    @Value("${spring.basicauthentication.password}")
    private String basicAuthenticationPassword;

    /**
     * Method to verify the given credentials. Credentials can be either coming from ATM (card-id, pin) or
     * Online banking (username, password). This method makes a use of the login-service via Feign client.
     * It returns either the logged in user's data, in case of success. Or NULL, in case of failure.
     *
     * @param credentials contains user's credentials
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Authenticate system users by verifying their login credentials")
    @RequestMapping(value = "/api/main-service/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public User login(@RequestBody Credentials credentials) {

//        boolean usernameAlreadyExists = false;
//        if(usernameAlreadyExists) {
//            throw new IllegalArgumentException("error.username");
//        }

        FeignClient feignClient = getFeignClient("/api/login-service/login");

        return feignClient.loginServiceVerifyLogin(credentials);
    }

    /**
     * Return the logged in user's data upon need. It returns either the requested user's data,
     * in case it is found, Or empty object, in case of failure.
     *
     * @param username contains user's username
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Fetchs user's data based on username")
    @RequestMapping(value = "/api/main-service/users/{username}",
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public User getUser(@PathVariable String username) {

        FeignClient feignClient = getFeignClient("/api/login-service/users");

        return feignClient.getUserDetails(username);
    }

    /**
     * Method to retrieve all transactions related to account based on the client-id. All
     * kind of transaction returned sorted according timestamp. If no results found, empty
     * Account object is returned.
     *
     * @param clientId contains client-id
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Retrieves all account details and transactions related to the given client-id")
    @RequestMapping(value = "/api/main-service/account/{clientId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Account getAccountDetails(@PathVariable String clientId) {

        FeignClient feignClient = getFeignClient("/api/account-service/account/");

        Account accountDetailsX = feignClient.getAccountDetailsFromClient(clientId);
        int x=0;
        return accountDetailsX;
    }

    /**
     * Prepare Feign-client for communication with other services
     * @param path
     * @return
     */
    private FeignClient getFeignClient(String path) {

        Feign.Builder builder = Feign.builder();
        Feign.Builder client = builder.client(new OkHttpClient());
        Feign.Builder encoder = client.encoder(new GsonEncoder());
        Feign.Builder decoder = encoder.decoder(new GsonDecoder());
        Feign.Builder requestInterceptor = decoder.requestInterceptor(getRequestInterceptor());
        Feign.Builder logger = requestInterceptor.logger(new Slf4jLogger(FeignClient.class)).logLevel(feign.Logger.Level.FULL);
        FeignClient feignClient = logger.target(FeignClient.class, feignUrl + path);

        return feignClient;
    }

    private BasicAuthRequestInterceptor getRequestInterceptor() {
        return new BasicAuthRequestInterceptor(basicAuthenticationUsername, basicAuthenticationPassword);
    }

    ////    @Bean
//    public DataSource dataSource() throws SQLException {
//        if (dbUrl == null || dbUrl.isEmpty()) {
//            return new HikariDataSource();
//        } else {
//            HikariConfig config = new HikariConfig();
//            config.setJdbcUrl(dbUrl);
//            config.setUsername("b9579c6ae9cba0");
//            config.setPassword("89a88141");
//            return new HikariDataSource(config);
//        }
//    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

//    public ResponseEntity handle() {
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
