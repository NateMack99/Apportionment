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
        sc.useDelimiter(",|\r\n|\n|\r");
        System.out.println(sc.next());
        System.out.println(sc.next());

        ArrayList<State> stateArr = new ArrayList<>();

        while (sc.hasNext()) {
            String state = sc.next();
            int population = sc.nextInt();
            stateArr.add(new State(state, population));
        }

        Apportionment ap = new Apportionment(stateArr, representatives);
        ap.calculateRepresentatives();
        System.out.println(ap);
    }


}