package com.abcbankinggroup.DTOs;

import java.util.ArrayList;

/**
 * <h1>Data Transfer Object: FuncdTransferResponse</h1>
 *
 * <p>
 * Contains all related attributes and their getters and setters
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
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
