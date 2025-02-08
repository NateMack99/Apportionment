package edu.virginia.sde.hw1;

public class ErrorHandler {
    enum CustomError{
        INVALID_ARGUMENTS,
        FILE_NOT_FOUND,
        INVALID_INPUT,
        INSUFFICIENT_REPS,
        ZERO_POPULATION
    }

    static String usageMessage = "Usage: Apportionment.java [input.csv/xlsx] [reps(optional)]";
    static String inputFormatMessage = "Input file format:\n[Col1], [Col2]\n[Type 1], [Type 2]\n[Type 1], [Type 2]\n...";

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
            case INVALID_INPUT -> {
                errorMessage = "Invalid input file!";
                helpMessage = inputFormatMessage;
            }
            case INSUFFICIENT_REPS -> {
                errorMessage = "Insufficient representatives!";
                helpMessage = "The Hunter-Hill algorithm requires at least as many representatives as states";
            }
            case ZERO_POPULATION -> {
                errorMessage = "Unable to calculate!";
                helpMessage = "Sum of population can't be zero";
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
