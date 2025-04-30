package com.pluralsight;
import java.io.*;

public class Main {

    public static void main(String[] args){

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
                // System.out.println(input); // (Prints each line for confirmation if needed)
            }

            // Closes bufReader; release resources
            bufReader.close();
        }
        catch (IOException e){

            // Displays stack trace if error occurs
            e.printStackTrace();
        }

        Console.displayHomeScreen();
//        Console.displayLedger();
//        Console.displayReports();


//        Test input: "04/29/2025", "10:56:02", "Weekly stipend", "YearUpUnited via MyWisely", 200.00

    }
}
