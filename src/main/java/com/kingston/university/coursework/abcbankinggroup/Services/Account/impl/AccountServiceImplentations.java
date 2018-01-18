package com.kingston.university.coursework.abcbankinggroup.Services.Account.impl;

import com.kingston.university.coursework.abcbankinggroup.Connection.DatabaseConnectionSingleton;
import com.kingston.university.coursework.abcbankinggroup.DTOs.Account;
import com.kingston.university.coursework.abcbankinggroup.DTOs.TransactionList;
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
import java.util.HashMap;

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
    public Account retrieveAccountTransactions(String clientId) throws SQLException {

        DataSource dataSource = getDataSource();
        Connection connection = null;
        Account account = new Account();
        TransactionList transactionList = new TransactionList();
        HashMap<Integer, TransactionList> transactionListHashMap = new HashMap<>();

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * " +
                            "FROM account " +
                            "WHERE client_id = '" + clientId + "'");

            while (resultSet.next()) {
                transactionList.setId(resultSet.getInt("id"));
                transactionList.setClientType(resultSet.getString("client_type"));
                transactionList.setTransactionUUID(resultSet.getString("transaction_uuid"));
                transactionList.setTransactionType(resultSet.getString("transaction_type"));
                transactionList.setTransactionAmount(resultSet.getString("transaction_amount"));
                transactionList.setUnixtimestamp(resultSet.getString("timestamp"));
                transactionList.setClientType(resultSet.getString("client_type"));
                transactionList.setDoneBy(resultSet.getString("done_by"));

                account.setClientId(clientId);

                transactionListHashMap.put(resultSet.getInt("id"), transactionList);
            }

            account.setTransactionList(transactionListHashMap);

            return account;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            connection.close();
        }

        return account;
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
