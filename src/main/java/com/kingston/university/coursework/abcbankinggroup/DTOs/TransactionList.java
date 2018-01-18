package com.kingston.university.coursework.abcbankinggroup.DTOs;

public class TransactionList {

    private int id;
    private String transactionUUID;
    private String transactionType;
    private String transactionAmount;
    private String unixtimestamp;
    private String doneBy;
    private String clientType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionUUID() {
        return transactionUUID;
    }

    public void setTransactionUUID(String transactionUUID) {
        this.transactionUUID = transactionUUID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getUnixtimestamp() {
        return unixtimestamp;
    }

    public void setUnixtimestamp(String unixtimestamp) {
        this.unixtimestamp = unixtimestamp;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
