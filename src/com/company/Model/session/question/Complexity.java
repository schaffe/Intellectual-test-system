package com.company.Model.session.question;

import com.company.Model.session.logic.Distribution;

import java.util.Random;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public enum Complexity {

    easy(1, 30, 1.6), medium(2, 60, 1.3), hard(3, 100, 1.);

 /*   private static final int EASY_VALUE = 1;
    private static final int MEDIUM_VALUE = 2;
    private static final int HARD_VALUE = 3;*/
    private final int value;
    private final double multiplier;
    private final int points;

    private Complexity(final int newValue, int points, double multiplier) {
        value = newValue;
        this.multiplier = multiplier;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public int getValue() {
        return value;
    }

    public static Complexity get(double points) {
        double[] prob = Distribution.getInstance().getProbabilities(points);
        ComplexityRandomSelector rand = new ComplexityRandomSelector(prob);
        return rand.get();
        //return getByPoints(points);
    }
    private static Complexity getByPoints(double points) {
        if(points < easy.points) {
            return easy;
        } else if (points < medium.points) {
            return medium;
        } else {
            return hard;
        }
    }

    public static Complexity get(int value) {
        switch (value) {
            default:
                return medium;
            case 1:
                return easy;
            case 2:
                return medium;
            case 3:
                return hard;
        }
    }

    static class ComplexityRandomSelector {
        private double[] doubleArray;
        private Random rand;

        ComplexityRandomSelector(double[] doubleArray) {
            this.doubleArray = doubleArray;
            rand = new Random();

        }

        public Complexity get() {
            int totalSum = 0;
            for (Double item : doubleArray) {
                totalSum += Math.round(item);
            }
            int index = rand.nextInt(totalSum);
            double sum = 0;
            int i=0;
            while(sum < index ) {
                sum = sum + doubleArray[i++];
            }

            return Complexity.get(i);
        }
    }

    public static void main(String... args) {
        for (int i=0; i<=100; i += 5) {
            System.out.println(Complexity.get((double)i));
        }
    }
    }
