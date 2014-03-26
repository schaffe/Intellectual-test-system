package com.company.Model.session.logic;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 3/25/14
 */
public class Distribution {
    NormalDistribution dist100;
    NormalDistribution dist50;
    NormalDistribution dist0;

    private static class Holder{
        static final Distribution INSTANCE = new Distribution();
    }

    public static Distribution getInstance() {
        return Holder.INSTANCE;
    }

    private Distribution() {
        dist0 = new NormalDistribution(0, 30);
        dist50 = new NormalDistribution(50, 30);
        dist100 = new NormalDistribution(100, 30);
    }

    public static void main(String... args) {
        int num = 0;
        Distribution dist = Distribution.getInstance();


        for(int i = 0; i <= 100; i+=5) {
//            System.out.printf("%s %.0f ",i , dist.function(dist.dist0, i));
//            System.out.printf("%.0f ", dist.function(dist.dist0, i));

//            System.out.printf("%.7f ", dist.function(dist.dist50, i));
//            System.out.printf("%.0f ", dist.function(dist.dist100, i));
            System.out.println();
        }
        //System.out.println(Arrays.toString(array));



    }

    private double function(NormalDistribution distribution, double num) {
        return distribution.density(num)*10000;
    }

    private double function2(NormalDistribution distribution, double num) {
        return distribution.density(num);
    }

    public double[] getProbabilities(double num) {
        double[] array = new double[3];
        array[0] = function(dist0, num);
        array[1] = function(dist50, num);
        array[2] = function(dist100, num);
        return array;
    }
}
