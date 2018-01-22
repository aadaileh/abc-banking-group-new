package com.kingston.university.coursework.abcbankinggroup.DTOs;

import java.util.ArrayList;

public class FundTransferResponse {

    private boolean results;
    private ArrayList<String> errors;
    private double balance;
    private boolean performTransferStatus;
    private boolean updateAccountStatus;

    public boolean isResults() {
        return results;
    }

    public void setResults(boolean results) {
        this.results = results;
    }

    public ArrayList<String> getError() {
        return errors;
    }

    public void setError(ArrayList<String> errors) {
        this.errors = errors;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isPerformTransferStatus() {
        return performTransferStatus;
    }

    public void setPerformTransferStatus(boolean performTransferStatus) {
        this.performTransferStatus = performTransferStatus;
    }

    public boolean isUpdateAccountStatus() {
        return updateAccountStatus;
    }

    public void setUpdateAccountStatus(boolean updateAccountStatus) {
        this.updateAccountStatus = updateAccountStatus;
    }
}
