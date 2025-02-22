package edu.virginia.sde.hw1;

import java.util.Comparator;

public class State {

    private String name;
    private int population;
    private int reps = 0;
    private double remainder = 0;

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void addRep() {
        reps++;
    }

    public double getRemainder() {
        return remainder;
    }

    public void setRemainder(double remainder) {
        this.remainder = remainder;
    }

    public State(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public double calcPriority() {
        return (double) population / Math.sqrt(reps * (reps + 1));
    }

    public void clear() {
        reps = 0;
        remainder = 0;
    }
    @Override
    public String toString() {
        return name + " - " + reps;
    }
}

