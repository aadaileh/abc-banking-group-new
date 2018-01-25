package com.abcbankinggroup.Services.Account.impl;

import com.abcbankinggroup.Factory.CommonFactoryAbstract;
import com.abcbankinggroup.DTOs.Transaction;
import com.abcbankinggroup.DTOs.FundTransferRequest;
import com.abcbankinggroup.Services.Account.AccountServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

/**
 * <h1>Account service implementations</h1>
 *
 * <p>
 * Contains the implementation of all members of the Account-Service
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@Service
public class AccountServiceImplentations extends CommonFactoryAbstract {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceController.class);

    @Autowired
    private DataSource dataSource;

    /**
     * Method to retrieve the list of all transactions related to
     * an account based on the given client-id
     *
     * @param clientId client-id
     * @return account
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public ArrayList<Transaction> retrieveAccountDetails(String clientId) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * " +
                            "FROM account " +
                            "WHERE client_id = '" + clientId + "'" +
                            "ORDER BY timestamp ASC");

            while (resultSet.next()) {

                String client_id = resultSet.getString("client_id");
                Transaction transaction = new Transaction();
                int id = resultSet.getInt("id");

                transaction.setId(id);
                transaction.setClientId(client_id);
                transaction.setClientType(resultSet.getString("client_type"));
                transaction.setTransactionUUID(resultSet.getString("transaction_uuid"));
                transaction.setTransactionType(resultSet.getString("transaction_type"));
                transaction.setTransactionAmount(resultSet.getString("transaction_amount"));
                transaction.setUnixTimeStamp(resultSet.getString("timestamp"));
                transaction.setClientType(resultSet.getString("client_type"));
                transaction.setDoneBy(resultSet.getString("done_by"));

                transactions.add(transaction);
            }

            return transactions;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            connection.close();
        }

        return transactions;
    }

    /**
     * Method to retrieve the account balance based on the given client-id
     *
     * @param clientId client-id
     * @return Float account balance
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public double retrieveAccountBalance(String clientId) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        String transactionType = null;
        Float transactionAmount = null;
        double balance = 0;

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * " +
                            "FROM account " +
                            "WHERE client_id = '" + clientId + "'");

            while (resultSet.next()) {
                transactionType = resultSet.getString("transaction_type");
                transactionAmount = resultSet.getFloat("transaction_amount");

                if (transactionType.equals("w")){
                    transactionAmount = transactionAmount * -1;
                }
                balance += transactionAmount;
            }

            return balance;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            connection.close();
        }

        return balance;
    }

    /**
     * Update the account by adding new withdrawal transaction
     *
     * @param fundTransferRequest contains client-id
     * @return boolean
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Boolean updateAccountTable(FundTransferRequest fundTransferRequest) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            int resultSet = stmt.executeUpdate(
                    "INSERT INTO `account` (" +
                            "`transaction_uuid`, " +
                            "`client_id`, " +
                            "`transaction_type`, " +
                            "`transaction_amount`, " +
                            "`timestamp`, " +
                            "`done_by`, " +
                            "`client_type`) " +
                            "VALUES (" +
                            "'" + UUID.randomUUID().toString() + "', " +
                            "'" + fundTransferRequest.getClientId() + "', " +
                            "'" + fundTransferRequest.getTransactionType() + "', " +
                            "" + fundTransferRequest.getAmount() + ", " +
                            "NOW(), " +
                            "'" + fundTransferRequest.getBeneficiaryFullName() + "', " +
                            "'" + fundTransferRequest.getClientType() + "');");

            return true;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return false;

        } finally {
            connection.close();
        }
    }
}
