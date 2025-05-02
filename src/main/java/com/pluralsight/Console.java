package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

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
        String userChoice = scanner.nextLine();

        // EXECUTES SELECTED TASK
        // Upon D selection, performs a deposit
        if (userChoice.equalsIgnoreCase("D")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"Add a deposit\"");
            printDelayedEllipsis();

            // Prompts for and stores info from user for a deposit
            System.out.print("Please enter the deposit information below: \n");

            // Adds transaction (a deposit) to ledger
            Transaction.addTransaction(getUserTransaction());

            // Confirmation message
            System.out.print("Deposit successfully added! Returning to home screen");
            printDelayedEllipsis();

            // Re-displays home screen
            displayHomeScreen();
        }

        // Upon P selection, performs a payment
        else if (userChoice.equalsIgnoreCase("P")) {

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

            // Confirmation message
            System.out.print("Payment saved! Returning to home screen");
            printDelayedEllipsis();

            // Re-displays home screen
            displayHomeScreen();
        }

        // Upon L selection, displays ledger screen
        else if (userChoice.equalsIgnoreCase("L")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"View Ledger\"");
            printDelayedEllipsis();

            // Brings user to ledger screen
            displayLedger();
        }

        // Upon X selection, exits application
        else if (userChoice.equalsIgnoreCase("X")) {
            // Echos user choice
            System.out.println("\n~ Thank you for stopping by, and have a wonderful rest of your day!");

            // Exits app
        }

        // Upon any incorrect input, error message and re-displays options
        else {

            // If user does not want to retry
            if (offerRetryOption()){

                // Re-displays home screen
                displayHomeScreen();
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
                   (H) Home
               
                Your choice:\s""");


        // Stores user's choice
        String userChoice = scanner.nextLine();

        // EXECUTES SELECTED TASK
        // Upon A selection,  displays all entries
        if(userChoice.equalsIgnoreCase("A")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"View All Entries\"");
            printDelayedEllipsis();

            // Prints every transaction in the csv file in reverse (newest on top)
            for (int i = getArrayOfAllTransactions().size() - 1; i >= 0; i--){ // Goes from most recent entry to the oldest entry
                System.out.println(getArrayOfAllTransactions().get(i)); // Prints each line
            }

            // Re-displays ledger
            displayLedger();
        }

        // Upon D selection, displays all deposits
        else if (userChoice.equalsIgnoreCase("D")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"View Deposits\"");
            printDelayedEllipsis();

            // Initializes string array to store elements of parsed line
            String[] parsedLine;

            // Prints every transaction in the csv file in reverse (newest on top)
            for (int i = getArrayOfAllTransactions().size() - 1; i >= 0; i--){ // Goes from most recent entry to the oldest entry

                // Parses line by pipe symbol
                parsedLine = getArrayOfAllTransactions().get(i).split("\\|");

                // Converts amount from string to double
                double parsedAmount = Double.parseDouble(parsedLine[4]);

                // If transaction is a deposit (i.e. its amount is positive), displays it
                if(parsedAmount > 0){
                    System.out.println(getArrayOfAllTransactions().get(i));
                }
            }

            // Re-displays ledger
            displayLedger();
        }

        // Upon P selection, displays all payments
        else if (userChoice.equalsIgnoreCase("P")) {

            // Echos user's selection
            System.out.print("\nYou have selected \"View Payments\"");
            printDelayedEllipsis();

            // Initializes string array to store elements of parsed line
            String[] parsedLine;

            // Prints every transaction in the csv file in reverse (newest on top)
            for (int i = getArrayOfAllTransactions().size() - 1; i >= 0; i--){ // Goes from most recent entry to the oldest entry

                // Parses line by pipe symbol
                parsedLine = getArrayOfAllTransactions().get(i).split("\\|");

                // Converts amount from string to double
                double parsedAmount = Double.parseDouble(parsedLine[4]);

                // If transaction is a deposit (i.e. its amount is positive), displays it
                if(parsedAmount < 0){
                    System.out.println(getArrayOfAllTransactions().get(i));
                }
            }

            // Re-displays ledger
            displayLedger();
        }

        // Upon R selection, displays reports screen
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

            displayHomeScreen();  // Displays home screen
        }

        // Upon any incorrect input, error message and re-displays options
        else {

            // If user does not want to retry
            if (offerRetryOption()){
                // Re-displays ledger
                displayLedger();
            }

            // Otherwise, exits app

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
                   (0) Back
                
                Your choice:\s""");

        // Initializes variable for holding input
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        try {

            // Creates FileReader object connected to csv file
            FileReader filereader = new FileReader("src/main/resources/transactions.csv");
            // Creates BufferedReader object connected
            BufferedReader bufReader = new BufferedReader(filereader);

            // Initializes variable for holding input
            String input;

            // Upon input 1 (Month to Date), shows all transactions from the start of the current month up to today
            if (userChoice == 1) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Month to Date\"");
                printDelayedEllipsis();

                // Initializes string array to store elements of parsed line
                String[] parsedLine;

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){

                    // Parses line by pipe symbol
                    parsedLine = input.split("\\|");

                    // Selects date from parsed line; parses date by dash symbol
                    String[] parsedDate = parsedLine[0].split("-");

                    // Selects year from parsed date; converts it to an int (for comparing)
                    int year = Integer.parseInt(parsedDate[0]);

                    // Selects month from parsed date; converts it to an int (for comparing)
                    int month = Integer.parseInt(parsedDate[1]);

                    // If transaction's year & month are the current year & month, print the transaction
                    if (year == (LocalDate.now()).getYear() && month == (LocalDate.now()).getMonthValue()){   // LocalDate.now() required as an instance for getMonth to be called
                        System.out.println(input);
                    }
                }
            }

            // Upon input 2 (Previous Month), shows all transactions that occurred during the entire month before the current one
            else if (userChoice == 2) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Previous Month\"");
                printDelayedEllipsis();

                // Initializes string array to store elements of parsed line
                String[] parsedLine;

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){

                    // Parses line by pipe symbol
                    parsedLine = input.split("\\|");

                    // Selects date from parsed line; parses date by dash symbol
                    String[] parsedDate = parsedLine[0].split("-");

                    // Selects year from parsed date; converts it to an int (for comparing)
                    int year = Integer.parseInt(parsedDate[0]);

                    // Selects month from parsed date; converts it to an int
                    int month = Integer.parseInt(parsedDate[1]);

                    // If transaction's year is current year & month is previous month, print the transaction
                    if (year == (LocalDate.now()).getYear() && month == ((LocalDate.now()).getMonthValue()) - 1){   // LocalDate.now() required as an instance for getMonth to be called
                        System.out.println(input);
                    }
                }
            }

            // Upon input 3 (Year to Date), shows all transactions from January 1 of the current year up to today
            else if (userChoice == 3) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Year to Date\"");
                printDelayedEllipsis();

                // Initializes string array to store elements of parsed line
                String[] parsedLine;

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){

                    // Parses line by pipe symbol
                    parsedLine = input.split("\\|");

                    // Selects date from parsed line; parses date by dash symbol
                    String[] parsedDate = parsedLine[0].split("-");

                    // Selects year from parsed date; converts it to an int (for comparing)
                    int year = Integer.parseInt(parsedDate[0]);

                    // Selects month from parsed date; converts it to an int (for comparing)
                    int month = Integer.parseInt(parsedDate[1]);

                    // If transaction's year is current year & month is January (1), print the transaction
                    if (year == (LocalDate.now()).getYear() && month == 1){   // LocalDate.now() required as an instance for getMonth to be called
                        System.out.println(input);
                    }
                }
            }

            // Upon input 4 (Previous Year), shows all transactions from the full previous calendar year
            else if (userChoice == 4) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Previous Year\"");
                printDelayedEllipsis();

                // Initializes string array to store elements of parsed line
                String[] parsedLine;

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){

                    // Parses line by pipe symbol
                    parsedLine = input.split("\\|");

                    // Selects date from parsed line; parses date by dash symbol
                    String[] parsedDate = parsedLine[0].split("-");

                    // Selects year from parsed date; converts it to an int (for comparing)
                    int year = Integer.parseInt(parsedDate[0]);

                    // If transaction's year is previous year
                    if (year == ((LocalDate.now()).getYear()) - 1){   // LocalDate.now() required as an instance for getMonth to be called
                        System.out.println(input);
                    }
                }
            }

            // Upon input 5 (Search by Vendor), prompt the user for the vendor name and displays all entries for that vendor
            else if (userChoice == 5) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Search by Vendor\"");
                printDelayedEllipsis();

                // Prompts user for vendor name
                System.out.print("What is the name of the vendor you would like to search by?: ");
                String chosenVendor = scanner.nextLine();
                System.out.println(); // Skip line

                // Echos user's vendor selection
                System.out.print("Got it! Displaying results for " + chosenVendor);
                printDelayedEllipsis();

                // Initializes string array to store elements of parsed line
                String[] parsedLine;

                // Reads until there are no more lines of data
                while((input = bufReader.readLine()) != null){

                    // Parses line by pipe symbol
                    parsedLine = input.split("\\|");

                    // Selects date from parsed line; parses date by dash symbol
                    String vendor = parsedLine[3];

                    // If transaction's year is previous year
                    if (vendor.equalsIgnoreCase(chosenVendor)){   // LocalDate.now() required as an instance for getMonth to be called
                        System.out.println(input);
                    }
                }
            }

            // Upon input 6,
            else if (userChoice == 6) {

                // Echos user's selection
                System.out.print("\nYou have selected \"Custom Search\"");
                printDelayedEllipsis();

                // Prompts for and stores info from user for a deposit
                System.out.println("Please fill any details you'd like to search by below: (press Enter to skip)");

                System.out.print("Start Date: ");
                String startDate = scanner.nextLine();

                System.out.print("End Date: ");
                String endDate = scanner.nextLine();

                System.out.print("Description: ");
                String description = scanner.nextLine();

                System.out.print("Vendor: ");
                String vendor = scanner.nextLine();

                System.out.print("Amount: ");
                String amount = scanner.nextLine();

                // If amount is not an empty string (""), convert it into a double

                // Confirmation message
                System.out.print("Got it! Filtering transactions");
                printDelayedEllipsis();





            }

            // Upon input 0, re-displays reports page
            else if (userChoice == 0) {

                // Echos user's selection
                System.out.print("\nReturning to the ledger page!");
                printDelayedEllipsis();

                // Displays ledger
                displayLedger();
            }

            // Upon any incorrect input, error message and re-displays options
            else {

                if (offerRetryOption()) {
                    // Goes back to home screen
                    displayHomeScreen();
                }

                // Otherwise, exits
            }
        }

        // Prints error message upon encountering an error
        catch (IOException e){

            System.out.println("Oops! There's been an error fetching the reports. Please give me a moment");
            printDelayedEllipsis();
            displayReports();    // Tries again to display reports
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

            System.out.println(); // Skips a line

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

    private static ArrayList<String> getArrayOfAllTransactions(){

        ArrayList<String> arrayOfAllTransactions = new ArrayList<>();

        // READS IN CSV FILE
        try {

            // Creates FileReader object connected to csv file
            FileReader filereader = new FileReader("src/main/resources/transactions.csv");
            // Creates BufferedReader object connected
            BufferedReader bufReader = new BufferedReader(filereader);

            // Initializes variable for holding input
            String input;


            // Reads until there are no more lines of data
            while((input = bufReader.readLine()) != null){
                arrayOfAllTransactions.add(input); // Adds each line (transaction) to array of transactions
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


        // Returns array of all transactions (as strings) in the csv file
        return arrayOfAllTransactions;

    }
}
