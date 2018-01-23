package com.abcbankinggroup.DTOs;

import java.util.HashMap;

public class Account {

    private String clientId;
    private HashMap<Integer, Transaction> TransactionList;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public HashMap<Integer, Transaction> getTransactionList() {
        return TransactionList;
    }

    public void setTransactionList(HashMap<Integer, Transaction> transactionList) {
        TransactionList = transactionList;
    }
}
