package com.abcbankinggroup.Clients;

import com.abcbankinggroup.DTOs.FundTransferRequest;
import com.abcbankinggroup.DTOs.FundTransferResponse;
import com.abcbankinggroup.DTOs.Transaction;
import com.abcbankinggroup.DTOs.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeignClientTest {

    private static final String NAME = "some-name";
    private static final String ADDRESS = "some-address";
    private static final String CLIENT_TYPE = "some-client-type";
    private static final String DONE_BY = "some-done-by";
    private static final double ACCOUNT_BALANCE = 500;
    private static final String BANK_NAME = "some-bank-name";

    @Mock
    FundTransferRequest fundTransferRequestMock;

    @Mock
    private FeignClient feignClientMock;

    @Before
    public void setUp() throws Exception {

        User user = new User();
        user.setName(NAME);
        user.setAddress(ADDRESS);

        Transaction transaction = new Transaction();
        transaction.setClientType(CLIENT_TYPE);
        transaction.setDoneBy(DONE_BY);
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        FundTransferRequest fundTransferRequestMock = new FundTransferRequest();
        fundTransferRequestMock.setAvailableBalance(ACCOUNT_BALANCE);
        fundTransferRequestMock.setAmount(300);
        fundTransferRequestMock.setBankName(BANK_NAME);

        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        fundTransferResponse.setResults(true);

        when(feignClientMock.getUserDetails(anyString())).thenReturn(user);
        when(feignClientMock.getAccountDetailsFromClient(anyString())).thenReturn(transactions);
        when(feignClientMock.getAccountBalance(anyString())).thenReturn(ACCOUNT_BALANCE);
        when(feignClientMock.getAndCount()).thenReturn(true);
        when(feignClientMock.deliverCash()).thenReturn(true);
        when(feignClientMock.verifyTransfer(fundTransferRequestMock)).thenReturn(fundTransferResponse);
    }

    @Test
    public void getUserDetailsTest() {

        User userDetails = feignClientMock.getUserDetails(anyString());

        String name = userDetails.getName();
        String address = userDetails.getAddress();
        assertTrue(name.equals(NAME));
        assertTrue(address.equals(ADDRESS));
    }

    @Test
    public void getAccountDetailsFromClientTest() {

        ArrayList<Transaction> transactions = feignClientMock.getAccountDetailsFromClient(anyString());

        assertTrue(transactions.size() > 0);
    }

    @Test
    public void getAccountBalanceTest() {

        double accountBalance = feignClientMock.getAccountBalance(anyString());

        assertEquals(ACCOUNT_BALANCE, accountBalance, 0);
    }

    @Test
    public void getAndCountTest() {

        boolean getAndCount = feignClientMock.getAndCount();

        assertTrue(getAndCount);
    }

    @Test
    public void deliverCashTest() {

        boolean deliverCash = feignClientMock.deliverCash();

        assertTrue(deliverCash);
    }
}