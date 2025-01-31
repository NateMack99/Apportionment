package edu.virginia.sde.hw1;

public class ErrorHandler {
    enum CustomError{
        INVALID_ARGUMENTS,
        FILE_NOT_FOUND
    }

    static String usageMessage = "Usage: Apportionment.java [input.csv] [reps(optional)]";

    static void error(CustomError e) {
        String errorMessage = "Invalid error message?";
        switch (e) {
            case FILE_NOT_FOUND -> errorMessage = "File not found!";
            case INVALID_ARGUMENTS -> errorMessage = "Invalid arguments!";
        }

        System.err.println("ERROR: " + errorMessage);
    }
}
