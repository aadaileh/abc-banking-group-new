package com.abcbankinggroup.DTOs;

/**
 * <h1>Data Transfer Object: FundTransferRequest</h1>
 *
 * <p>
 * Contains all related attributes and their getters and setters
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
public class FundTransferRequest {

    private double availableBalance;
    private String clientId;
    private String transactionType; //'d' or 'w'
    private String clientType; //'atm' or 'online-banking'
    private String iban;
    private String swift;
    private String beneficiaryFullName;
    private String beneficiaryAddress;
    private String country;
    private String city;
    private String bankName;
    private String branch;
    private String currencyType;
    private double amount;
    private String transferPurpose;
    private String transferOn;
    private String notes;

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public String getBeneficiaryAddress() {
        return beneficiaryAddress;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransferPurpose() {
        return transferPurpose;
    }

    public void setTransferPurpose(String transferPurpose) {
        this.transferPurpose = transferPurpose;
    }

    public String getTransferOn() {
        return transferOn;
    }

    public void setTransferOn(String transferOn) {
        this.transferOn = transferOn;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
