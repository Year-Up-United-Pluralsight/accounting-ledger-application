package com.pluralsight;

public class Transaction {

    // PRIVATE VARIABLES
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // CONSTRUCTOR
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // GETTER AND SETTER FUNCTIONS ---------------------------------------------------------------------------------------
    // Amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Vendor
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Time
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

// MORE METHODS ------------------------------------------------------------------------------------------------------

    // Adds/saves a deposit transaction to the csv file
    public void addDeposit(Transaction transaction){

    }

    // Adds a debit transaction to the csv file
    public void makePayment(Transaction transaction){

    }

}