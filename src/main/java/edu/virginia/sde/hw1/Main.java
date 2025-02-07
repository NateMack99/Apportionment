package edu.virginia.sde.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

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
            throw new FileNotFoundException();
        }

        //Skip first line of file
        sc.useDelimiter("\r\n|\r|\n");

        int nameIndex = -1;
        int populationIndex = -1;

        String firstLine = sc.next();
        String[] line = firstLine.split(",");
        try {
            String col1 = line[0].trim();
            String col2 = line[1].trim();
            if (col1.equals("State")) {
                nameIndex = 0;
                populationIndex = 1;
                if (!col2.equals("Population")) {
                    throw new RuntimeException();
                }
            } else if (col1.equals("Population")) {
                nameIndex = 1;
                populationIndex = 0;
                if (!col2.equals("State")) {
                    throw new RuntimeException();
                }
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            ErrorHandler.error(ErrorHandler.CustomError.INVALID_CSV);
            return;
        }


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
            String[] curLine = state.split(",");
            String name;
            int population;
            //Check for correct formatting
            try {
                name = curLine[nameIndex].trim();
                population = Integer.parseInt(curLine[populationIndex].trim());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                continue;
            }
            stateArr.add(new State(name, population));
        }

        Apportionment ap = new Apportionment(stateArr, representatives);
        ap.hamiltonAlg();
        System.out.println(ap);

    }


}