package com.kingston.university.coursework.abcbankinggroup.Services.Transaction.Account.impl;

import com.kingston.university.coursework.abcbankinggroup.Connection.DatabaseConnectionSingleton;
import com.kingston.university.coursework.abcbankinggroup.DTOs.FundTransferRequest;
import com.kingston.university.coursework.abcbankinggroup.DTOs.FundTransferResponse;
import com.kingston.university.coursework.abcbankinggroup.Services.Transaction.Account.TransactionServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class TransactionServiceImplentations {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

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

        if(fundTransferRequest.getIban().isEmpty()) {
            errors.add("IBAN is missing");
            fundTransferResponse.setResults(false);
            LOG.error("fund transfer check failure: IBAN is missing");
        }

        if(fundTransferRequest.getSwift().isEmpty()) {
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

        DataSource dataSource = getDataSource();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
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

            LOG.debug(e.getMessage());
            return false;

        } finally {
            connection.close();
        }
    }


    /**
     * Build connection to database
     * @return Datasource to the database
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    private DataSource getDataSource() throws SQLException {
        DatabaseConnectionSingleton databaseConnectionSingleton = DatabaseConnectionSingleton.getInstance();
        databaseConnectionSingleton.setDbUrl(dbUrl);
        databaseConnectionSingleton.setUsername(username);
        databaseConnectionSingleton.setPassword(password);
        return databaseConnectionSingleton.dataSource();
    }
}
