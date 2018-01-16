package abcbankinggroup;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@CrossOrigin(origins = "*", maxAge = 3600)
//@EnableSwagger2
public class MainServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    /**
     * Add some comments
     */
    @ApiOperation("Create a new Lead")
    @RequestMapping(value = "/api/main/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.") })
    public User register(@RequestBody User user) {

        /**
         * This is the way how to create UUID!!!!!
         * To be used for creating transaction's UUID later on!!!!!!!
         */
        UUID.randomUUID().toString();

        boolean usernameAlreadyExists = false;
        if(usernameAlreadyExists) {
            throw new IllegalArgumentException("error.username");
        }
        User returnedUser = new User();

        returnedUser.setAddress(user.getAddress());
        returnedUser.setLastName(user.getLastName());
        returnedUser.setMail(user.getMail());
        returnedUser.setName(user.getName());
        returnedUser.setPassword(user.getPassword());

        return returnedUser;
    }

    /**
     * Add some comments
     */
    @ApiOperation("Create a new Lead")
    @RequestMapping(value = "/api/main/db",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.") })

    public String db() throws SQLException {

        DataSource dataSource = dataSource();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks2 (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            connection.close();

            return "ok, added";

        } catch (Exception e) {

            LOG.debug(e.getMessage());
        }
        return "";
    }


    /**
     * Add some comments
     */
    @ApiOperation("Create a new Lead")
    @RequestMapping(value = "/api/main/call-login-service",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 405, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.") })
    public User callTheNewServiceOnTheCloud() {

        FeignClient bookClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(getRequestInterceptor())
                .logger(new Slf4jLogger(FeignClient.class))
                .target(FeignClient.class, "http://localhost:8080/api/login-service/register-new");

        User user = bookClient.loginService(getSampleUser());
        return user;
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

//    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setUsername("b9579c6ae9cba0");
            config.setPassword("89a88141");
            return new HikariDataSource(config);
        }
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
