package com.abcbankinggroup.Clients;

import com.abcbankinggroup.DTOs.*;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.ArrayList;

/**
 * <h1>FeignClient Interface</h1>
 *
 * <p>
 * Interface contains a declaration of all service calls done from the main-service towards other services.
 * The communication is done using the NetFlix FeigClient.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@org.springframework.cloud.netflix.feign.FeignClient
public interface FeignClient {
    @RequestLine("GET /{username}")
    User getUserDetails(@Param("username") String username);

    @RequestLine("GET /{clientId}")
    @Headers("Content-Type: application/json")
    ArrayList<Transaction> getAccountDetailsFromClient(@Param("clientId") String clientId);

    @RequestLine("GET /{clientId}")
    @Headers("Content-Type: application/json")
    double getAccountBalance(@Param("clientId") String clientId);

    @RequestLine("GET /api/account-service/get-and-count/")
    @Headers("Content-Type: application/json")
    boolean getAndCount();

    @RequestLine("GET /api/account-service/deliver-cash")
    @Headers("Content-Type: application/json")
    boolean deliverCash();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    FundTransferResponse verifyTransfer(FundTransferRequest fundTransferRequest);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    Boolean performTransfer(FundTransferRequest fundTransferRequest);

    @RequestLine("PUT")
    @Headers("Content-Type: application/json")
    Boolean updateAccountTable(FundTransferRequest fundTransferRequest);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    User authenticationServiceVerifyLogin(Credentials credentials);
}