package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Console {

    static Scanner scanner = new Scanner(System.in);

    // Displays home screen options; then displays the selected option
    public static void displayHomeScreen() {

        // Displays home screen options; prompts user for choice
        System.out.print("""
                ~ Welcome to the Accounting Ledger! How can I help you...
                   (D) Add a Deposit
                   (P) Make a Payment (Debit)
                   (L) View Ledger
                   (X) Exit
                Your choice:\s""");

        // Stores user's choice
        String input = scanner.nextLine();

        // EXECUTES SELECTED TASK
        // Upon D selection, performs a deposit
        if (input.equalsIgnoreCase("D")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"Add a deposit\"");
            printDelayedEllipsis();

            // Prompts for and stores info from user for a deposit
            System.out.print("Please enter the deposit information below: \n");

            // Adds transaction (a deposit) to ledger
            Transaction.addTransaction(getUserTransaction());
        }

        // Upon P selection, performs a payment
        else if (input.equalsIgnoreCase("P")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"Make a Payment\"");
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

        // Upon L selection, displays ledger screen
        else if (input.equalsIgnoreCase("L")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"View Ledger\"");
            printDelayedEllipsis();

            displayLedger();
        }

        // Upon X selection, exits application
        else if (input.equalsIgnoreCase("X")) {
            // Echos user choice
            System.out.println("\n~ Thank you for stopping by, and have a wonderful rest of your day!");
        }

        // Upon any incorrect input, error message and re-displays options
        else {

            if (offerRetryOption()){
                // Goes back to home screen
                displayHomeScreen();
            }

            else {
                return;
            }
        }

    }

    // Displays ledger options; then displays the selected option (all entries show the newest entry first)
    public static void displayLedger(){

        // Displays ledger options; prompts user for choice
        System.out.println("""
                Please select what you'd like to do next:
                   (A) All
                   (D) Deposits
                   (P) Payments
                   (R) Reports
                   (H) Home""");


        // Stores user's choice
        String userChoice = scanner.nextLine();

        // READS IN CSV FILE
        try {

            // Creates FileReader object connected to csv file
            FileReader filereader = new FileReader("src/main/resources/transactions.csv");
            // Creates BufferedReader object connected
            BufferedReader bufReader = new BufferedReader(filereader);

            // Initializes variable for holding input
            String input;


            // EXECUTES SELECTED TASK
            // Upon A selection,  displays all entries
            if(userChoice.equalsIgnoreCase("A")) {

                // Echos user's selection
                System.out.print("\nYou have selected \"View All Entries\"");
                printDelayedEllipsis();

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){
                    System.out.println(input); // Prints each line
                }
            }

            // Upon D selection, displays all deposits
            else if (userChoice.equalsIgnoreCase("D")) {

                // Echos user's selection
                System.out.print("\nYou have selected \"View Deposits\"");
                printDelayedEllipsis();

                // Initializes string array to store elements of parsed line
                String[] parsedLine;

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){

                    // Parses line by pipe symbol
                    parsedLine = input.split("\\|");

                    // Converts amount from string to double
                    double parsedAmount = Double.parseDouble(parsedLine[4]);

                    // If transaction is a deposit (i.e. its amount is positive), displays it
                    if(parsedAmount > 0){
                        System.out.println(input);
                    }
                }
            }

            // Upon P selection, displays all payments
            else if (userChoice.equalsIgnoreCase("P")) {

                // Echos user's selection
                System.out.print("\nYou have selected \"View Payments\"");
                printDelayedEllipsis();

                // Initializes string array to store elements of parsed line
                String[] parsedLine;

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){

                    // Parses line by pipe symbol
                    parsedLine = input.split("\\|");

                    // Converts amount from string to double
                    double parsedAmount = Double.parseDouble(parsedLine[4]);

                    // If transaction is a deposit (i.e. its amount is positive), displays it
                    if(parsedAmount < 0){
                        System.out.println(input);
                    }
                }
            }

            // Upon R selection, displays reports or runs a custom search
            else if (userChoice.equalsIgnoreCase("R")) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Reports\"");
                printDelayedEllipsis();

                System.out.println(); // Skips line
                displayReports();  // Displays reports
            }

            // Upon H selection, displays home screen again
            else if (userChoice.equalsIgnoreCase("H")) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Home\"");
                printDelayedEllipsis();

                System.out.println(); // Skips line
                displayHomeScreen();  // Displays home screen
            }

            // Upon any incorrect input, error message and re-displays options
            else {

                // If user wants to try again; redirects back to ledger screen
                if (offerRetryOption()){
                    // Goes back to ledger
                    displayLedger();
                }

                // Otherwise, exits
                else {
                    return;
                }

            }

            // Closes bufReader; release resources
            bufReader.close();
        }

        // Prints error message upon encountering an error
        catch (IOException e){

            System.out.println("Oops! There's been an error fetching the ledger. Please give me a moment");
            printDelayedEllipsis();
            displayLedger();    // Tries again to display ledger
        }
    }

    // Displays reports; then displays the selected option
    public static void displayReports(){

        // Displays report options; prompts user for choice
        System.out.println("""
                Please select what you'd like to do next:
                   (1) Month To Date
                   (2) Previous Month
                   (3) Year To Date
                   (4) Previous Year
                   (5) Search by Vendor
                   (6) Custom Search
                   (0) Back""");

        // Initializes variable for holding input
        int input = scanner.nextInt();
        scanner.nextLine();

        // Upon input 1,
        if (input == 1){

        }
        // Upon input 2,
        else if (input == 2){

        }

        // Upon input 3,
        else if (input == 3){

        }

        // Upon input 4,
        else if (input == 4){

        }

        // Upon input 5,
        else if (input == 5){

        }

        // Upon input 6,
        else if (input == 6){

        }

        // Upon input 0,
        else if (input == 0){

        }

        // Upon any incorrect input, error message and re-displays options
        else {

            if (offerRetryOption()){
                // Goes back to home screen
                displayHomeScreen();
            }

            else {
                return;
            }
        }
    }

// HELPER METHODS
    // Adds delayed ellipsis for screen transitions
    private static void printDelayedEllipsis(){
        // Prints delayed ellipsis...
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print(".");     // Prints three periods (an ellipsis)
                Thread.sleep(315);   // Delays printing of each period
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

    // Returns true if user wants to try again to input a valid selection; return false otherwise (both include accompanying console prints)
    private static boolean offerRetryOption() {

        // Checks if user wants to either 1) see options again or 2) exit
        System.out.print("\nSorry! I'm not sure I understand what you mean by that. Would you like to try again? (Y/N): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Y")){

            // Echos user choice
            System.out.print("\nGreat! Let’s give it another go");
            printDelayedEllipsis();

            return true;
        }

        else if (response.equalsIgnoreCase("N")){
            // Echos user choice
            System.out.println("\n~ Thank you for stopping by, and have a wonderful rest of your day!");

            return false;
        }

        else {
            // If input is not Y or N, repeats prompt
            return offerRetryOption();
        }

    }

}
