package edu.virginia.sde.hw1;

import java.nio.file.attribute.UserPrincipal;
import java.sql.PseudoColumnUsage;

public class ErrorHandler {
    enum CustomError{
        INVALID_ARGUMENTS,
        FILE_NOT_FOUND,
        INVALID_CSV
    }

    static String usageMessage = "Usage: Apportionment.java [input.csv] [reps(optional)]";
    static String csvFormatMessage = "Input file format: [State], [Populations]";

    static void error(CustomError e) {
        String errorMessage = "Invalid error message?";
        String helpMessage = "";
        switch (e) {
            case FILE_NOT_FOUND -> {
                errorMessage = "File not found!";
                helpMessage = usageMessage;
            }
            case INVALID_ARGUMENTS -> {
                errorMessage = "Invalid arguments!";
                helpMessage = usageMessage;
            }
            case INVALID_CSV -> {
                errorMessage = "Invalid input file!";
                helpMessage = csvFormatMessage;
            }
        }

        System.err.println("ERROR: " + errorMessage);
        if (!helpMessage.equals("")) {
            System.out.println(helpMessage);
        }

    }
}
