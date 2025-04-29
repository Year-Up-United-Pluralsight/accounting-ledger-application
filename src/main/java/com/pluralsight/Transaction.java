package com.pluralsight;
import java.io.*;

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

    // Adds/saves a transaction to the csv file
    // Deposit = money gained (all positive amounts on leder) & Payment = money spent (all negative amounts on ledger)
    public static void addTransaction(Transaction transaction){
        try {

            // Opens the file
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true); // "true" appends the file instead of overwriting

            // Create a BufferedWriter
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            // Writes to the file
            bufWriter.write("\n" + transaction.date + "|" + transaction.time + "|" + transaction.description + "|" + transaction.vendor + "|" + String.format("%.2f", transaction.amount)); // String.format to keep 2 decimal places

            // close the file when you are finished using it
            bufWriter.close();
        }

        catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.getStackTrace();
        }
    }


}