package edu.virginia.sde.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Apportionment {

    private final ArrayList<State> stateArr;
    private int reps;
    private int populationSum = 0;

    Apportionment(ArrayList<State> arr, int reps) {
        stateArr = arr;
        this.reps = reps;
    }

    //Sorts states by remainder
    public void sort() {
        stateArr.sort(Comparator.comparing(State::getRemainder));
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

    public void calculateRepresentatives() {
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
