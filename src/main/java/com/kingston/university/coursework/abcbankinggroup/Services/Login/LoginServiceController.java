package com.kingston.university.coursework.abcbankinggroup.Services.Login;

import com.kingston.university.coursework.abcbankinggroup.Clients.FeignClient;
import com.kingston.university.coursework.abcbankinggroup.DTOs.User;
import com.kingston.university.coursework.abcbankinggroup.Services.Login.impl.LoginServiceImplentations;
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
public class LoginServiceController extends LoginServiceImplentations {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoginServiceImplentations loginService;

    /**
     * Add some comments
     */
    @ApiOperation("Create a new Lead")
    @RequestMapping(value = "/api/login-service/db",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.") })
    public String db() throws SQLException {
        int x = 0;
        return loginService.dbService();
    }


    /**
     * Add some comments
     */
    @ApiOperation("Create a new Lead")
    @RequestMapping(value = "/api/login-service/call-the-new-service-on-the-cloud",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 405, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.") })
    public void callTheNewServiceOnTheCloud() {

        FeignClient bookClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(getRequestInterceptor())
                .logger(new Slf4jLogger(FeignClient.class))
                .target(FeignClient.class, "https://warm-harbor-89034.herokuapp.com/db");

        bookClient.loginService(getSampleUser());
    }

    private BasicAuthRequestInterceptor getRequestInterceptor() {
        return new BasicAuthRequestInterceptor("apiuser", "pass");
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://warm-harbor-89034.herokuapp.com").build();
    }

    private User getSampleUser() {
        User returnedUser = new User();

        returnedUser.setAddress("address");
        returnedUser.setLastName("lastname");
        returnedUser.setMail("email");
        returnedUser.setName("name");
        returnedUser.setPassword("password");

        return returnedUser;
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
