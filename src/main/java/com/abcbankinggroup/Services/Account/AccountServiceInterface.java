package com.abcbankinggroup.Services.Account;

import com.abcbankinggroup.DTOs.Transaction;
import com.abcbankinggroup.DTOs.FundTransferRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Main interface for the Account-Service. It describes and defines all needed
 * methods for the mentioned service.
 *
 * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 */
@Service
@EnableSwagger2
public interface AccountServiceInterface {

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
    public ArrayList<Transaction> getAccount(@PathVariable String clientId) throws SQLException;


    /**
     * Method to retrieve the latest balance of the account
     *
     * @param clientId contains client-id
     * @return Float the latest balance
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public double getBalance(@PathVariable String clientId) throws SQLException;


    /**
     * Method to update the account records by adding the latest transferred fund
     *
     * @param fundTransferRequest contains all fund transfer fields
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Boolean updateAccountTable(@RequestBody FundTransferRequest fundTransferRequest) throws SQLException;


    /**
     * Method to take the deposit put in the ATM and count it.
     *
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Boolean getAndCount();


    /**
     * Method to deliver the cash mechanically after a successful withdrawal action via an ATM.
     *
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Boolean deliverCashByATM();
}
