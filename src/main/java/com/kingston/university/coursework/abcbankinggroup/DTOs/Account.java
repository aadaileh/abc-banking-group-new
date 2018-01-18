package com.kingston.university.coursework.abcbankinggroup.DTOs;

import java.util.HashMap;

public class Account {

    private String clientId;
    private HashMap<Integer, TransactionList> TransactionList;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public HashMap<Integer, TransactionList> getTransactionList() {
        return TransactionList;
    }

    public void setTransactionList(HashMap<Integer, TransactionList> transactionList) {
        TransactionList = transactionList;
    }
}
