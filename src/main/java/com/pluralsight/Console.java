package com.pluralsight;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Console {

    static Scanner scanner = new Scanner(System.in);

    // Displays home screen options; then displays the selected option
    public static void displayHomeScreen() {

        // Displays home screen options; prompts user for choice
        System.out.print("~ Welcome to the Accounting Ledger! How can I help you...\n" +
                "   (D) Add a Deposit\n" +
                "   (P) Make a Payment (Debit)\n" +
                "   (L) View Ledger\n" +
                "   (X) Exit\n" +
                "Your choice: ");

        // Stores user's choice
        String input = scanner.nextLine();

        // Upon 'D' input, performs a deposit
        if (input.equals("D")) {

            // Echos user's selection
            System.out.print("\nYou've selected \"Add a deposit\"");
            printDelayedEllipsis();

            // Prompts for and stores info from user for a deposit
            System.out.print("Please enter the deposit information below: \n");

            // Adds transaction (a deposit) to ledger
            Transaction.addTransaction(getUserTransaction());
        }

        // Upon 'P' input, performs a payment
        else if (input.equals("P")) {

            // Echos user's selection
            System.out.print("\nYou've selected \"Make a Payment\"");
            printDelayedEllipsis();

            // Prompts for and stores info from user for a deposit
            System.out.print("Please enter the debit information below: \n");

            // Converts the transaction's amount to negative
            Transaction transaction = getUserTransaction();     // Prompts user for a transaction
            double negativeAmount = -(transaction.getAmount()); // Calculates negative equivalent of "amount" in transaction
            transaction.setAmount(negativeAmount);              // Sets "amount" to its negative equivalent

            // Adds transaction (a payment) to ledger
            Transaction.addTransaction(transaction);
        }

        // Upon 'L' input, displays ledger screen
        else if (input.equals("L")) {

            // Echos user's selection
            System.out.print("\nYou've selected \"View Ledger\"");
            printDelayedEllipsis();

            displayLedger();
        }

        // Upon 'X' input, exits application
        else if (input.equals("X")) {
            // Echos user choice
            System.out.println("\n~ Thank you for stopping by, and have a wonderful rest of your day!");
        }

        // Upon incorrect inputs, error message and re-displays options
        else {

            // Checks if user wants to either 1) see options again or 2) exit
            System.out.print("\nSorry! I'm not sure I understand what you mean by that. Would you like to try again? (Y/N): ");
            String response = scanner.nextLine();

            if (response.equals("Y")){

                // Echos user choice
                System.out.print("\nGreat! Let’s give it another go");
                printDelayedEllipsis();

                // Goes back to home screen
                displayHomeScreen();
            }

            else {
                // Echos user choice
                System.out.println("\n~ Thank you for stopping by, and have a wonderful rest of your day!");
            }
        }

    }

    // Displays ledger options; then displays the selected option (all entries show the newest entry first)
    public static void displayLedger(){

        // Displays ledger options; prompts user for choice
        System.out.println("Please select what you'd like to do next:\n" +
                "(A) All\n" +
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

// HELPER METHODS
    // Adds delayed ellipsis for screen transitions
    private static void printDelayedEllipsis(){
        // Prints delayed ellipsis...
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print(".");     // Prints three periods (an ellipsis)
                Thread.sleep(350);   // Delays printing of each period
            }

            System.out.println("\n");          // Skips two lines
        }
        catch(InterruptedException e){
            System.out.println("Error");
        }
    }

    // Prompts user for the details of a Transaction; returns the Transaction
    private static Transaction getUserTransaction() {

    // Prompts user for the details of their transaction
        System.out.print("   Description: ");
            String description = scanner.nextLine();

            System.out.print("   Vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("   Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

    // Constructs a Transaction using prompt details; returns the Transaction
        return new Transaction(description, vendor, amount);

    }

}
