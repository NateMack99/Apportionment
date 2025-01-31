package edu.virginia.sde.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Checks for correct # of arguments
        if(args.length < 1) {
            ErrorHandler.error(ErrorHandler.CustomError.INVALID_ARGUMENTS);
            System.out.println(ErrorHandler.usageMessage + "\nExiting...");
            return;
        }

        Scanner sc;
        //Checks for valid File
        try {
            sc = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            ErrorHandler.error(ErrorHandler.CustomError.FILE_NOT_FOUND);
            System.out.println( ErrorHandler.usageMessage + "\nExiting...");
            return;
        }

        //Skip first line of file
        sc.useDelimiter("\r\n");
        sc.next();

        int representatives = 435;

        //Sets custom # of representatives if provided
        if(args.length > 1) {
            try {
                representatives = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                ErrorHandler.error(ErrorHandler.CustomError.INVALID_ARGUMENTS);
                System.out.println( ErrorHandler.usageMessage + "\nExiting...");
                return;
            }
        }

        //Reads states
        ArrayList<State> stateArr = new ArrayList<>();
        while (sc.hasNext()) {
            String state = sc.next();
            String[] line = state.split(",");
            stateArr.add(new State(line[0], Integer.parseInt(line[1])));

        }

        Apportionment ap = new Apportionment(stateArr, representatives);
        ap.calculateRepresentatives();
        System.out.println(ap);
    }


}