package com.abcbankinggroup.Clients;

import com.abcbankinggroup.DTOs.*;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.ArrayList;

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