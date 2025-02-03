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
            throw new RuntimeException();
        }

        Scanner sc;
        //Checks for valid File
        try {
            sc = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            ErrorHandler.error(ErrorHandler.CustomError.FILE_NOT_FOUND);
            throw new RuntimeException();
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
                throw new RuntimeException();
            }
        }

        //Reads states
        ArrayList<State> stateArr = new ArrayList<>();
        while (sc.hasNext()) {
            String state = sc.next();
            String[] line = state.split(",");
            String name;
            int population;
            //Check for correct formatting
            try {
                name = line[0];
                population = Integer.parseInt(line[1]);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                continue;
            }
            stateArr.add(new State(name, population));
        }

        Apportionment ap = new Apportionment(stateArr, representatives);
        ap.calculateRepresentatives();
        System.out.println(ap);
    }


}