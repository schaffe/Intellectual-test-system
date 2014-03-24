package com.company.Model.session.logic;

import com.company.Model.session.question.Complexity;

import java.util.LinkedList;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 3/23/14
 */
class TopicStatistic {
    public final static int MAX_RATE = 100;
    private final static int MIN_RATE = 5;
    private final static int DEFAULT_RATE = 45;
    private LinkedList<StatisticTableRow> data;

    public TopicStatistic() {
        data = new LinkedList<>();
    }

    public Complexity addQuestion(boolean result) {
        int complexityId;
        if(data.isEmpty()) {
            complexityId = 2;
        } else {
            complexityId = getNextComplexity().getValue();
        }
        Complexity complexity = Complexity.get(complexityId);
        if(result) {
            addCompletedQuestion(complexity);
        } else {
            addFailedQuestion(complexity);
        }
        return getNextComplexity();
    }

    private void addFailedQuestion(Complexity complexity) {
        StatisticTableRow row = calculateRowFailed(complexity.getValue(), 0, complexity.getMultiplier());
        data.add(row);
    }

    private void addCompletedQuestion(Complexity complexity) {
        StatisticTableRow row = calculateRowCompleted(complexity.getValue(), complexity.getPoints(), complexity.getMultiplier());
        data.add(row);
    }

    private StatisticTableRow calculateRowCompleted(int complexity, int points, double multiplier) {
        int prevTotal;
        double prevMul;
        if(data.isEmpty()) {
            prevTotal = 0;
            prevMul = 0;
        } else {
            prevTotal = data.getLast().getTotal();
            prevMul = data.getLast().getMultiplier();
        }

        int newTotal = (int) ((prevTotal + points) * multiplier);
        double newMul = prevMul + multiplier;
        double rate = newTotal / newMul;


        return new StatisticTableRow(complexity, points, newMul, newTotal, rate);
    }

    private StatisticTableRow calculateRowFailed(int complexity, int points, double multiplier) {
        int prevTotal;
        double prevMul;
        if(data.isEmpty()) {
            prevTotal = 0;
            prevMul = 0;
        } else {
            prevTotal = data.getLast().getTotal();
            prevMul = data.getLast().getMultiplier();
        }

        int newTotal = prevTotal + points;
        double newMul = prevMul + 1;
        double rate = newTotal / newMul;

        return new StatisticTableRow(complexity, points, newMul, newTotal, rate);
    }


    private Complexity getNextComplexity() {
        double rate = data.getLast().getRate();
        return Complexity.get(rate);
    }

    public double getRaring() {
        if (data.isEmpty()) {
            return DEFAULT_RATE;
        } else {
            return data.getLast().getRate();
        }
    }

    public static void main(String... args) {
        TopicStatistic table = new TopicStatistic();
        table.addQuestion(true);
        /*for (int i = 0; i < 1000; i++){
            System.out.println(table.data.getLast());
            //table.addQuestion(getRandomBoolean(), table.getNextComplexity());

            table.addQuestion(false, table.getNextComplexity());
            //table.addQuestion(true, table.getNextComplexity());
        }*/

        for (int i = 0; i < 20; i++){
            System.out.println(table.data.getLast());
            //table.addQuestion(getRandomBoolean(), table.getNextComplexity());

            //table.addQuestion(false, table.getNextComplexity());
            table.addQuestion(true);
        }


    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result
    }
}
