package edu.virginia.sde.hw1;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        boolean hamilton = false;
        int representatives = 435;

        //Checks for correct # of arguments
        if(args.length < 1) {
            ErrorHandler.error(ErrorHandler.CustomError.INVALID_ARGUMENTS);
            throw new RuntimeException();
        }
        if (args.length == 2) {
            if (args[1].equals("--hamilton")) {
                hamilton = true;
            } else {
                try {
                    representatives = Integer.parseInt(args[1]);
                } catch (IllegalArgumentException e) {
                    ErrorHandler.error(ErrorHandler.CustomError.INVALID_ARGUMENTS);
                    System.exit(0);
                }
            }
        }
        if (args.length == 3) {
            if (args[2].equals("--hamilton")) {
                hamilton = true;
            } else {
                ErrorHandler.error(ErrorHandler.CustomError.INVALID_ARGUMENTS);
                System.exit(0);
            }
        }

        String[] fileSplit = args[0].split("\\.");
        String extension = fileSplit[fileSplit.length - 1];
        ArrayList<State> stateArr;

        try {
            if (extension.equals("csv")) {
                stateArr = readCSV(args[0]);
            } else if (extension.equals("xlsx")) {
                stateArr = readXLSX(args[0]);
            } else {throw new FileNotFoundException();};
        } catch (IOException e) {
            ErrorHandler.error(ErrorHandler.CustomError.FILE_NOT_FOUND);
            return;
        }


        Apportionment ap = new Apportionment(stateArr, representatives);
        if (hamilton) {
            ap.hamiltonAlg();
        } else {
            ap.hhAlg();
        }
        System.out.println(ap);
    }



    public static ArrayList<State> readCSV(String filePath) throws FileNotFoundException {
        Scanner sc;
        //Checks for valid File
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            ErrorHandler.error(ErrorHandler.CustomError.FILE_NOT_FOUND);
            throw new FileNotFoundException();
        }

        sc.useDelimiter("\r\n|\r|\n");

        int nameIndex = -1;
        int populationIndex = -1;

        String firstLine = sc.next();
        List<String> line = Arrays.asList(firstLine.split(","));

        //get rid of stupid invisible characters
        line = line.stream()
                .map(String::trim)
                .map(s -> s.replaceAll("[^\\x00-\\x7F]", "")).toList();

        nameIndex = line.indexOf("State");
        populationIndex = line.indexOf("Population");

        //Check for invalid format
        if (nameIndex < 0 || populationIndex < 0) {
            ErrorHandler.error(ErrorHandler.CustomError.INVALID_INPUT);
            System.exit(0);
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
                ErrorHandler.error(ErrorHandler.CustomError.INVALID_INPUT);
                continue;
            }
            stateArr.add(new State(name, population));
        }
        return stateArr;
    }

    /*
    Took some code from this website:
    https://www.baeldung.com/java-xlsx-csv-conversion
     */
    public static Workbook openWorkbook(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return WorkbookFactory.create(fis);
        }
    }
    public static ArrayList<State> readXLSX(String filePath) throws IOException {
        Workbook workbook = openWorkbook(filePath);
        Sheet sheet = workbook.getSheetAt(0);
        List<String[]> data = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            String[] rowData = new String[row.getLastCellNum()];
            for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                Cell cell = row.getCell(cn);
                rowData[cn] = cell == null ? "" : formatter.formatCellValue(cell).trim();
            }
            data.add(rowData);
        }

        if (data.size() < 2) {
            ErrorHandler.error(ErrorHandler.CustomError.INVALID_INPUT);
            System.exit(0);
        }

        int nameIndex = -1;
        int populationIndex = -1;

        List<String> firstLine = Arrays.asList(data.get(0));

        nameIndex = firstLine.indexOf("State");
        populationIndex = firstLine.indexOf("Population");

        //Check for invalid format
        if (nameIndex < 0 || populationIndex < 0) {
            ErrorHandler.error(ErrorHandler.CustomError.INVALID_INPUT);
            System.exit(0);
        }

        ArrayList<State> stateArr = new ArrayList<>();

        for(int i = 1; i < data.size(); i++) {
            String name;
            int population;
            //Check for correct formatting
            try {
                name = data.get(i)[nameIndex];
                population = Integer.parseInt(data.get(i)[populationIndex]);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                ErrorHandler.error(ErrorHandler.CustomError.INVALID_INPUT);
                continue;
            }
            stateArr.add(new State(name, population));
        }

        workbook.close();
        return stateArr;
    }

}
