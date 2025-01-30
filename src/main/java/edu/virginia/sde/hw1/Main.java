package edu.virginia.sde.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length < 1) {
            System.out.println("Usage: Apportionment.java [input.csv] [reps(optional)]\nExiting...");
            return;
        }

        int representatives = 435;

        if(args.length > 1) {
            try {
                representatives = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
               System.out.println("Usage: Apportionment.java [input.csv] [reps(optional)]\nExiting...");
               return;
            }
        }

        Scanner sc = new Scanner(new File(args[0]));
        sc.useDelimiter("\r\n");
        sc.next();

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