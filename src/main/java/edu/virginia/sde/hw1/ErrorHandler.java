package edu.virginia.sde.hw1;

import java.nio.file.attribute.UserPrincipal;
import java.sql.PseudoColumnUsage;

public class ErrorHandler {
    enum CustomError{
        INVALID_ARGUMENTS,
        FILE_NOT_FOUND,
        INVALID_CSV,
        INSUFFICIENT_REPS
    }

    static String usageMessage = "Usage: Apportionment.java [input.csv] [reps(optional)]";
    static String csvFormatMessage = "Input file format:\n[Col1], [Col2]\n[Type 1], [Type 2]\n[Type 1], [Type 2]\n...";

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
            case INSUFFICIENT_REPS -> {
                errorMessage = "Insufficient representatives!";
                helpMessage = "The Hunter-Hill algorithm requires at least as many representatives as states";
            }
        }

        System.err.println("ERROR: " + errorMessage);
        System.err.flush();
        if (!helpMessage.equals("")) {
            //Sleeping to ensure correct ordering of messages
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(helpMessage);
        }

    }
}
