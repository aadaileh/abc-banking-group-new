package com.kingston.university.coursework.abcbankinggroup.Services.Account;

import com.kingston.university.coursework.abcbankinggroup.DTOs.Account;
import com.kingston.university.coursework.abcbankinggroup.Services.Account.impl.AccountServiceImplentations;
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
 * Main Controller for the Account-Service. It implements all needed
 * methods for the mentioned service.
 *
 * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 */
@RestController
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableSwagger2
public class AccountServiceController extends AccountServiceImplentations implements AccountServiceInterface {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceController.class);

    @Autowired
    private AccountServiceImplentations accountServiceImplentations;

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
    @ApiOperation("Retrieves all transaction related to client-id")
    @RequestMapping(value = "/api/account-service/{clientId}/transactions",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Account getAccount(@PathVariable String clientId) throws SQLException {

        return accountServiceImplentations.retrieveAccountTransactions(clientId);
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