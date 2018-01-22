package com.kingston.university.coursework.abcbankinggroup.Services.Account.impl;

import com.kingston.university.coursework.abcbankinggroup.Connection.DatabaseConnectionSingleton;
import com.kingston.university.coursework.abcbankinggroup.DTOs.FundTransferRequest;
import com.kingston.university.coursework.abcbankinggroup.DTOs.Transaction;
import com.kingston.university.coursework.abcbankinggroup.Services.Account.AccountServiceController;
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
import java.util.UUID;

@Service
public class AccountServiceImplentations {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

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

        DataSource dataSource = getDataSource();
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

        DataSource dataSource = getDataSource();
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

        DataSource dataSource = getDataSource();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
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
                            "'w', " +
                            "" + fundTransferRequest.getAmount() + ", " +
                            "NOW(), " +
                            "'" + fundTransferRequest.getBeneficiaryFullName() + "', " +
                            "'online-banking');");

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
