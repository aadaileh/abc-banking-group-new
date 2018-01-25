package com.abcbankinggroup.Services.Transaction;

import com.abcbankinggroup.DTOs.FundTransferRequest;
import com.abcbankinggroup.DTOs.FundTransferResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;

/**
 * <h1>Transaction Service Interface</h1>
 *
 * <p>
 * Main interface for the Transaction-Service. It describes and defines all needed
 * methods for the mentioned service.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@Service
@EnableSwagger2
public interface TransactionServiceInterface {

    /**
     * Method to verify the fund transfer by checking the items of the transfer including the sufficiency of the
     * balance.
     *
     * @param fundTransferRequest contains client-id
     * @return FundTransferResponse
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public FundTransferResponse verifyFundTransfer(@RequestBody FundTransferRequest fundTransferRequest);

    /**
     * Add the fund transfer details to the transfer table for later processing by external applications
     *
     * @param fundTransferRequest contains client-id
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Boolean performTransfer(@RequestBody FundTransferRequest fundTransferRequest) throws SQLException;


}
