package com.abcbankinggroup.Services.Transaction.impl;

import com.abcbankinggroup.Services.Transaction.TransactionServiceController;
import com.abcbankinggroup.Connection.DataSourceAbstract;
import com.abcbankinggroup.DTOs.FundTransferRequest;
import com.abcbankinggroup.DTOs.FundTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class TransactionServiceImplentations extends DataSourceAbstract {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceController.class);

    @Autowired
    private DataSource dataSource;

    /**
     * Method to verify the fund transfer by checking the items of the transfer including the sufficiency of the
     * balance.
     *
     * @param fundTransferRequest contains client-id
     * @return FundTransferResponse
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public FundTransferResponse verifyFundTransfer(FundTransferRequest fundTransferRequest) {

        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        ArrayList<String> errors = new ArrayList<>();
        fundTransferResponse.setResults(true);

        if(fundTransferRequest.getClientId().isEmpty()) {
            errors.add("Client-Id is missing");
            fundTransferResponse.setResults(false);
            LOG.error("fund transfer check failure: client-id is missing");
        }

        if(fundTransferRequest.getIban() != null && fundTransferRequest.getIban().isEmpty()) {
            errors.add("IBAN is missing");
            fundTransferResponse.setResults(false);
            LOG.error("fund transfer check failure: IBAN is missing");
        }

        if(fundTransferRequest.getSwift() != null && fundTransferRequest.getSwift().isEmpty()) {
            errors.add("SWIFT is missing");
            fundTransferResponse.setResults(false);
            LOG.error("fund transfer check failure: SWIFT is missing");
        }

        double availableBalance = fundTransferRequest.getAvailableBalance();
        if(availableBalance < fundTransferRequest.getAmount()) {
            errors.add("Available balance not enough");
            fundTransferResponse.setResults(false);
            LOG.error("fund transfer check failure: Available balance not enough");
        }

        LOG.info("fund transfer check successful");

        fundTransferResponse.setError(errors);
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
    public Boolean performTheTransfer(FundTransferRequest fundTransferRequest) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            int resultSet = stmt.executeUpdate(
                    "INSERT INTO `transfers` (" +
                            "`timestamp`, " +
                            "`client_id`, " +
                            "`beneficiary_full_name`, " +
                            "`beneficiary_address`, " +
                            "`country`, " +
                            "`city`, " +
                            "`bank_name`, " +
                            "`branch`, " +
                            "`amount`, " +
                            "`currency_type`, " +
                            "`purpose`, " +
                            "`transfer_on`, " +
                            "`notes`" +
                            ") VALUES (" +
                            "NOW(), " +
                            "'" + fundTransferRequest.getClientId() + "', " +
                            "'" + fundTransferRequest.getBeneficiaryFullName() + "', " +
                            "'" + fundTransferRequest.getBeneficiaryAddress() + "', " +
                            "'" + fundTransferRequest.getCountry() + "', " +
                            "'" + fundTransferRequest.getCity() + "', " +
                            "'" + fundTransferRequest.getBankName() + "', " +
                            "'" + fundTransferRequest.getBranch() + "', " +
                            "" + fundTransferRequest.getAmount() + ", " +
                            "'" + fundTransferRequest.getCurrencyType() + "', " +
                            "'" + fundTransferRequest.getTransferPurpose() + "', " +
                            "NOW(), " +
                            "'" + fundTransferRequest.getNotes() + "');");

            return true;

        } catch (Exception e) {
            connection.close();
            LOG.debug(e.getMessage());
            return false;

        } finally {
            connection.close();
        }
    }
}
