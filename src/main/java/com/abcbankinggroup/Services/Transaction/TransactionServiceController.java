package com.abcbankinggroup.Services.Transaction;

import com.abcbankinggroup.DTOs.FundTransferRequest;
import com.abcbankinggroup.DTOs.FundTransferResponse;
import com.abcbankinggroup.Services.Transaction.impl.TransactionServiceImplentations;
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
 * <h1>Transaction Service</h1>
 *
 * <p>
 * Main Controller for the Transaction-Service. It implements all needed
 * methods for the mentioned service, including getting, verifying different
 * transaction actions performed from both online-banking or ATM
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
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableSwagger2
public class TransactionServiceController extends TransactionServiceImplentations implements TransactionServiceInterface {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceController.class);

    @Autowired
    private TransactionServiceImplentations transactionServiceImplentations;

    /**
     * Method to verify the fund transfer by checking the items of the transfer including the sufficiency of the
     * balance.
     *
     * @param fundTransferRequest contains client-id
     * @return FundTransferResponse
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Verify all related and necessary information before processing the fund transfer")
    @RequestMapping(value = "/api/transaction-service/check",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public FundTransferResponse verify(@RequestBody FundTransferRequest fundTransferRequest) {

        FundTransferResponse fundTransferResponse = transactionServiceImplentations.verifyFundTransfer(fundTransferRequest);

        return fundTransferResponse;
    }

    /**
     * Add the fund transfer details to the transfer table for later processing by external applications
     *
     * @param fundTransferRequest contains client-id
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Create a fund-transfer record that can be consumed by external Fund-Transferring applications.")
    @RequestMapping(value = "/api/transaction-service/transaction/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Boolean performTransfer(@RequestBody FundTransferRequest fundTransferRequest) throws SQLException {

        Boolean performTransfer = transactionServiceImplentations.performTheTransfer(fundTransferRequest);

        return performTransfer;
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