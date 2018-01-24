package com.abcbankinggroup.Services.Account;

import com.abcbankinggroup.DTOs.FundTransferRequest;
import com.abcbankinggroup.DTOs.Transaction;
import com.abcbankinggroup.Services.Account.impl.AccountServiceImplentations;
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
import java.util.ArrayList;

/**
 * <h1>Account Service</h1>
 *
 * <p>
 * Main Controller for the Account-Service. It implements all needed
 * methods for the mentioned service including account's records, balance, and clients.
 * It serves the Main Service over the NetFlix Feign client.
 * </p>
 *
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
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
    @ApiOperation("Retrieves all account details and transactions related to client-id")
    @RequestMapping(value = "/api/account-service/account/{clientId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public ArrayList<Transaction> getAccount(@PathVariable String clientId) {

        ArrayList<Transaction> accountDetails = null;
        try {
            accountDetails = accountServiceImplentations.retrieveAccountDetails(clientId);
            LOG.info("account details for the clinet-id [" + clientId + "] is returned");
        } catch (SQLException e) {
            LOG.error("something went wrong while retrieving the account's details for the clinet-id ["
                    + clientId + "] Message: " + e.getMessage());
        }

        return accountDetails;
    }

    /**
     * Method to retrieve the current balance of the account
     *
     * @param clientId contains client-id
     * @return Float the latest balance
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Returns the account balance based on the given client-id")
    @RequestMapping(value = "/api/account-service/balance/{clientId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public double getBalance(@PathVariable String clientId) {

        double accountDetails = 0;
        try {
            accountDetails = accountServiceImplentations.retrieveAccountBalance(clientId);
            LOG.info("current account balance for the clinet-id [" + clientId + "] is returned");
        } catch (SQLException e) {
            LOG.error("something went wrong while retrieving the account's balance for the clinet-id ["
                    + clientId + "] Message: " + e.getMessage());
        }

        return accountDetails;
    }

    /**
     * Method to update the account records by adding the latest transferred fund
     *
     * @param fundTransferRequest contains all fund transfer fields
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Updates the account records by adding the latest transferred fund")
    @RequestMapping(value = "/api/account-service/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Boolean updateAccountTable(@RequestBody FundTransferRequest fundTransferRequest) {

        Boolean accountUpdated = false;

        try {
            accountUpdated = accountServiceImplentations.updateAccountTable(fundTransferRequest);
            LOG.info("account record successfully updated  for the clinet-id ["
                    + fundTransferRequest.getClientId() + "]");
        } catch (SQLException e) {
            LOG.error("something went wrong while updating the account record for the clinet-id ["
                    + fundTransferRequest.getClientId() + "] Message: " + e.getMessage());
        }

        return accountUpdated;
    }


    /**
     * Method to take the deposit put in the ATM and count it.
     *
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Receives the deposit and count it mechanically")
    @RequestMapping(value = "/api/account-service/get-and-count/",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Boolean getAndCount() {

        /**
         * This method simulates the mechanical process of getting the envelope and counting it.
         * I assume that this check returns TRUE.
         */

        return true;
    }

    /**
     * Method to deliver the cash mechanically after a successful withdrawal action via an ATM.
     *
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Delivers cash back after a successful withdrawal action via an ATM")
    @RequestMapping(value = "/api/account-service/deliver-cash",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Boolean deliverCashByATM() {

        /**
         * This method simulates the mechanical process of delivering the cash after a successful withdrawal action.
         * I assume that this check returns TRUE.
         */

        return true;
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    void handleSQLException(
            SQLException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}