package com.pluralsight;

import java.util.Scanner;

public class Console {

    static Scanner scanner = new Scanner(System.in);

    // Displays home screen options; then displays the selected option
    public static void displayHomeScreen(){

        // Displays home screen options; prompts user for choice
        System.out.println("Welcome! Please select what you'd like to do:\n" +
                "(D) Add Deposit\n" +
                "(P) Make Payment (Debit)\n" +
                "(L) Ledger\n" +
                "(X) Exit");

        // Stores user's choice
        String input = scanner.nextLine();


        switch(input){
            case "D":
                // Call addDeposit(Transaction transaction);
                break;

            case "P":

                break;

            case "L":

                break;

            case "X":

                break;

            default:
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

}
