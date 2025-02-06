package edu.virginia.sde.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Apportionment {

    private final ArrayList<State> stateArr;
    private int reps;
    private int populationSum = 0;

    Apportionment(ArrayList<State> arr, int reps) {
        stateArr = arr;
        this.reps = reps;
    }

    //Sorts states in alphabetical order
    public void alphabetize() {
        stateArr.sort(Comparator.comparing(State::getName));
    }

    private void calculatePopulation() {
        for (int i = 0; i < stateArr.size(); i++) {
            populationSum += stateArr.get(i).getPopulation();
        }
    }

    public void hamiltonAlg() {
        //Add initial representatives
        this.calculatePopulation();
        double remainderSum = 0;
        double remainingReps = reps;
            for (State state : stateArr) {
                int stateReps = (int)(((double) state.getPopulation() / populationSum) * reps);
                state.setReps(stateReps);
                double remainder = (((double)state.getPopulation() / populationSum) * reps) - stateReps;
                state.setRemainder(remainder);
                remainderSum += remainder;
                remainingReps -= stateReps;
            }

            stateArr.sort(Comparator.comparing(State::getRemainder).reversed());
            int i = 0;
            while(remainingReps > 0) {
                State state = stateArr.get(i);
                stateArr.get(i).addRep();
                i++;
                remainingReps--;
        }
    }

    public void hhAlg() {
        if(reps < stateArr.size()) {
            ErrorHandler.error(ErrorHandler.CustomError.INSUFFICIENT_REPS);
            System.exit(0);
        }
        stateArr.forEach(State::clear);

        int remainingReps = reps;
        for(int i = 0; i < stateArr.size(); i++) {
            stateArr.get(i).addRep();
            remainingReps--;
        }

        while(remainingReps > 0) {
            State highestPriority = Collections.max(stateArr, Comparator.comparing(State::calcPriority));
            highestPriority.addRep();
            remainingReps--;
        }
    }

    /*
    Format:
    [State1] - [#reps]
    [State2] - [#reps]
    ...
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        this.alphabetize();
        for (State state : stateArr) {
            out.append(state).append("\n");
        }
        return out.toString().trim();
    }
}

