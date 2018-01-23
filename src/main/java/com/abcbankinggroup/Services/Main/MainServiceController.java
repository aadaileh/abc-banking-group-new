package com.abcbankinggroup.Services.Main;

import com.abcbankinggroup.Clients.FeignClient;
import com.abcbankinggroup.DTOs.*;
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
import java.util.ArrayList;

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
            @ApiResponse(code = 201, message = "Authenticated"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public User login(@RequestBody Credentials credentials) {

        FeignClient feignClient = getFeignClient("/api/login-service/login");
        User user = feignClient.loginServiceVerifyLogin(credentials);

        LOG.info("Successfully logged in for credentials:" + credentials.getUsername() + ", " + credentials.getPassword());

        return user;
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
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
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
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public ArrayList<Transaction> getAccountDetails(@PathVariable String clientId) {

        FeignClient feignClient = getFeignClient("/api/account-service/account/");

        ArrayList<Transaction> accountDetails = feignClient.getAccountDetailsFromClient(clientId);
        return accountDetails;
    }

    /**
     * Method to perform a fund transfer process. Usually the fund-transfer process consists from the following steps:
     * 1) get the available balance, 2) get transfer details 3) check transfer details by (comparing balance to request)
     * and (verifying benificiary details). 4) Update Account. 5) Add record to the main transfer table 6) Print receipt.
     * All these methods will be available in both transaction-service and account-service. These will be called from here.
     *
     * @param fundTransferRequest transfer's all related fields
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Perform all necessary actions to perform a fund transfer")
    @RequestMapping(value = "/api/main-service/transfer-fund",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public FundTransferResponse transferFunds(@RequestBody FundTransferRequest fundTransferRequest) {

        //GET  /api/account-service/balance/{clientId}
        FeignClient feignClientAccountServiceBalance = getFeignClient("/api/account-service/balance/");
        double accountBalance = feignClientAccountServiceBalance.getAccountBalance(fundTransferRequest.getClientId());

        //POST /api/transaction-service/check
        FeignClient feignClientTransactionServiceCheck = getFeignClient("/api/transaction-service/check");
        fundTransferRequest.setAvailableBalance(accountBalance);
        FundTransferResponse fundTransferResponse = feignClientTransactionServiceCheck.verifyTransfer(fundTransferRequest);

        //POST /api/transaction-service/transaction/add
        FeignClient feignClientTransactionServiceAdd = getFeignClient("/api/transaction-service/transaction/add");
        Boolean performTransfer = feignClientTransactionServiceAdd.performTransfer(fundTransferRequest);
        fundTransferResponse.setPerformTransferStatus(true);

        //PUT /api/account-service/update
        if(performTransfer != null && performTransfer) {
            FeignClient feignClientAccountServiceUpdate = getFeignClient("/api/account-service/update/");
            feignClientAccountServiceUpdate.updateAccountTable(fundTransferRequest);
            fundTransferResponse.setUpdateAccountStatus(true);
        }

        fundTransferResponse.setBalance(accountBalance);
        return fundTransferResponse;
    }

    /**
     * Method to deposit funds to the account. Usually the deposit process consists from the following steps:
     * 1) Initiate the mechanical process to get and count the money. 2) Update Account records. 3) Print receipt.
     * All these methods will be available in both transaction-service and account-service. These will be called from here.
     *
     * @param fundTransferRequest transfer's all related fields
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Deposits money to own account via ATM")
    @RequestMapping(value = "/api/main-service/deposit",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public FundTransferResponse deposit(@RequestBody FundTransferRequest fundTransferRequest) {

        //1) Initaing the mechanical process to get and count the money
        FeignClient feignClientAccountServiceGetAndCount = getFeignClient("");
        boolean getAndCountResult = feignClientAccountServiceGetAndCount.getAndCount();

        //2) Update Account records.
        FeignClient feignClientAccountServiceUpdate = getFeignClient("/api/account-service/update/");
        feignClientAccountServiceUpdate.updateAccountTable(fundTransferRequest);

        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        fundTransferResponse.setUpdateAccountStatus(true);
        return fundTransferResponse;
    }

    /**
     * Method to withdraw money from ATM. Usually the withdrawal process consists from the following steps:
     * 1) get the available balance, 2) check transfer details by (comparing balance to request) 3) Update
     * Account. 4) Initiate mechanical process to deliver money 5) Print receipt.
     * All these methods will be available in both transaction-service and account-service. These will be called from here.
     *
     * @param fundTransferRequest transfer's all related fields
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Withdraws money from own account via ATM")
    @RequestMapping(value = "/api/main-service/withdraw",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public FundTransferResponse withdraw(@RequestBody FundTransferRequest fundTransferRequest) {

        //1) get the available balance
        FeignClient feignClientAccountServiceBalance = getFeignClient("/api/account-service/balance/");
        double accountBalance = feignClientAccountServiceBalance.getAccountBalance(fundTransferRequest.getClientId());

        //2) check transfer details by (comparing balance to request)
        FeignClient feignClientTransactionServiceCheck = getFeignClient("/api/transaction-service/check");
        fundTransferRequest.setAvailableBalance(accountBalance);
        FundTransferResponse fundTransferResponse = feignClientTransactionServiceCheck.verifyTransfer(fundTransferRequest);

        //3) Update Account.
        if(fundTransferResponse.isResults()) {
            FeignClient feignClientAccountServiceUpdate = getFeignClient("/api/account-service/update/");
            feignClientAccountServiceUpdate.updateAccountTable(fundTransferRequest);
            fundTransferResponse.setUpdateAccountStatus(true);
        }

        //4) Initiate mechanical process to deliver money
        FeignClient feignClientTransactionServiceDeliverCash = getFeignClient("");
        fundTransferRequest.setAvailableBalance(accountBalance);
        feignClientTransactionServiceDeliverCash.deliverCash();

        fundTransferResponse.setBalance(accountBalance);
        return fundTransferResponse;
    }


    /**
     * Prepare Feign-client for communication with other services
     * @param path
     * @return
     */
    private FeignClient getFeignClient(String path) {

        FeignClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(getRequestInterceptor())
                .logger(new Slf4jLogger(FeignClient.class))
                .logLevel(feign.Logger.Level.FULL)
                .target(FeignClient.class, feignUrl + path);

        return feignClient;
    }

    private BasicAuthRequestInterceptor getRequestInterceptor() {
        return new BasicAuthRequestInterceptor(basicAuthenticationUsername, basicAuthenticationPassword);
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
