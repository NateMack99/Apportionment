package edu.virginia.sde.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length < 1) {
            System.out.println("Failed");
            return;
        }

        Scanner sc = new Scanner(new File(args[0]));
        sc.useDelimiter("[,\n]");
        sc.next();
        sc.next();

        State[] stateArr = new State[50];

        for (int i = 0; i < 50; i++) {
            stateArr[i] = new State(sc.next(), sc.nextInt());
        }

        System.out.println(Arrays.toString(stateArr));


    }


}