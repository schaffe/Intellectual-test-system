package com.company.Model.session.logic;

import com.company.Model.session.question.Complexity;

import java.util.Random;

/**
 * Selecting random topic depends on rating of topic
 * i.e. if the rating is higher, the probability of getting this topic is lower.
 *
 * @author Artur Dzidzoiev
 * @version 3/24/14
 */
class ComplexityRandomSelector {
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
        if(i == 0) {
            System.err.println("///#get()@i==0 ");
        }
        return Complexity.get(doubleArray[i - 1]);
    }


}
