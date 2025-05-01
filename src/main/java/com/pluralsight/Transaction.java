package com.pluralsight;
import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Transaction {

    // PRIVATE VARIABLES
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // CONSTRUCTOR
    public Transaction(String description, String vendor, double amount) {
        this.date = LocalDate.now();  // Sets date to current date
        this.time = (LocalTime.now()).truncatedTo(ChronoUnit.SECONDS); // Sets time to current time; removes extra nanoseconds
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
    public LocalTime getTime() {
        return time;
    }

    // Date
    public LocalDate getDate() {
        return date;
    }

    // Sets date and time to current; since this will always be the case for every transaction
    public void setCurrentDateAndTime(String date, String time){

    }

// MORE METHODS ------------------------------------------------------------------------------------------------------

    // Adds/saves a transaction to the csv file
    // Deposit = money gained (all positive amounts on ledger) & Payment = money spent (all negative amounts on ledger)
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