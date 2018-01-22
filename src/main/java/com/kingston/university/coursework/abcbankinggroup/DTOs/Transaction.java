package com.kingston.university.coursework.abcbankinggroup.DTOs;

public class Transaction {

    private int id;
    private String clientId;
    private String transactionUUID;
    private String transactionType;
    private String transactionAmount;
    private String unixTimeStamp;
    private String doneBy;
    private String clientType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getUnixTimeStamp() {
        return unixTimeStamp;
    }

    public void setUnixTimeStamp(String unixTimeStamp) {
        this.unixTimeStamp = unixTimeStamp;
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
