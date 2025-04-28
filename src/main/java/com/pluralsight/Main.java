package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args){

        displayHomeScreen();

    }



    public static void displayHomeScreen(){

        System.out.println("Welcome! Please select what you would like to do:\n" +
                "(D) Add Deposit\n" +
                "(P) Make Payment (Debit)\n" +
                "(L) Ledger\n" +
                "(X) Exit");


    }
}
