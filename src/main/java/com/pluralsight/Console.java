package com.pluralsight;

import java.util.Scanner;

public class Console {

    static Scanner scanner = new Scanner(System.in);

    // Displays home screen options; then displays the selected option
    public static void displayHomeScreen(){

        // Displays home screen options; prompts user for choice
        System.out.print("~ Welcome to the Accounting Ledger! How can we help you...\n" +
                "   (D) Add a Deposit\n" +
                "   (P) Make a Payment (Debit)\n" +
                "   (L) View Ledger\n" +
                "   (X) Exit\n" +
                "Your choice: ");

        // Stores user's choice
        String input = scanner.nextLine();

        // Executes the task based on user's choice
        if (input.equals("D")) {

            // Echos user's selection
            System.out.print("\nYou've selected \"Add a deposit\"");
            printDelayedEllipsis();

            // Prompts for and stores info from user for a deposit
            System.out.print("\nPlease enter the deposit information below: \n");

            // Adds transaction (a deposit) to ledger
            Transaction.addTransaction(getUserTransaction());
        }

        else if (input.equals("P")) {

            // Echos user's selection
            System.out.print("\nYou've selected \"Make a Payment\"");
            printDelayedEllipsis();

            // Prompts for and stores info from user for a deposit
            System.out.print("\nPlease enter the debit information below: \n");

            // Converts the transaction's amount to negative
            Transaction transaction = getUserTransaction();     // Prompts user for a transaction
            double negativeAmount = -(transaction.getAmount()); // Calculates negative equivalent of "amount" in transaction
            transaction.setAmount(negativeAmount);              // Sets "amount" to its negative equivalent

            // Adds transaction (a payment) to ledger
            Transaction.addTransaction(transaction);
        }

        else if (input.equals("L")) {

        }

        else if (input.equals("X")) {

        }

        else {

        }

        }

    // Displays ledger options; then displays the selected option (all entries show the newest entry first)
    public static void displayLedger(){

        // Displays ledger options; prompts user for choice
        System.out.println("Please select what you'd like to do next:" +
                "(A) All" +
                "(D) Deposits\n" +
                "(P) Payments\n" +
                "(R) Reports\n" +
                "(H) Home");

        // Stores user's choice
        String input = scanner.nextLine();

        switch(input){
            case "A":

                break;

            case "D":

                break;

            case "P":

                break;

            case "R":

                break;

            case "H":

                break;

            default:
        }

    }

    // Displays reports; then displays the selected option
    public static void displayReports(){

        // Displays report options; prompts user for choice
        System.out.println("Please select what you'd like to do next:" +
                "(1) All" +
                "(2) Deposits\n" +
                "(3) Payments\n" +
                "(4) Reports\n" +
                "(5) Home\n" +
                "(6) Custom Search\n" +
                "(0) Back");



    }

    // Displays entries that are deposits
    private static void displayOnlyDeposits(){}

    // Displays entries that are payments
    private static void displayOnlyPayments(){}

    // Helper func for introducing next screen
    private static void printDelayedEllipsis(){
        // Prints delayed ellipsis...
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                Thread.sleep(400);
            }
        }
        catch(InterruptedException e){
            System.out.println("Error");
        }
    }

    // Prompts user for the details of a Transaction; returns the Transaction
    private static Transaction getUserTransaction() {

    // Prompts user for the details of their transaction
        System.out.print("   Date: ");
            String date = scanner.nextLine();

            System.out.print("   Time: ");
            String time = scanner.nextLine();

            System.out.print("   Description: ");
            String description = scanner.nextLine();

            System.out.print("   Vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("   Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

    // Constructs a Transaction using prompt details; returns the Transaction
        return new Transaction(date, time, description, vendor, amount);

    }

}
