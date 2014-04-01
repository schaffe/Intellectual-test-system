package com.company.Model.session.logic;

import com.company.Model.Config.Config;
import com.company.Model.session.question.Complexity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 3/23/14
 */
class TopicStatistic {
    private int correct;
    private List<StatisticTableRow> data;

    public TopicStatistic() {
        data = new ArrayList<>();
    }

    public TopicStatistic(double rate) {
        data = new ArrayList<>();
        data.add(new StatisticTableRow(0, 0, 0, rate));
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
            correct++;
            addCompletedQuestion(complexity);
        } else {
            addFailedQuestion(complexity);
        }
        return getNextComplexity();
    }

    private void addFailedQuestion(Complexity complexity) {
        StatisticTableRow row = calculateRowFailed(complexity.getValue(), complexity.getPoints(), complexity.getMultiplier());
        data.add(row);
    }

    private void addCompletedQuestion(Complexity complexity) {
        StatisticTableRow row = calculateRowCompleted2(complexity.getValue(), complexity.getPoints());
        data.add(row);
    }

    private StatisticTableRow calculateRowCompleted2(int complexity, int points) {
        double rate;
        if (data.isEmpty()) {
            rate = Config.DEFAULT_RATE;
        } else {
            rate = getLast().getRate();
        }

        rate += points;
        if(rate > Config.MAX_RATE) {
            rate = Config.MAX_RATE;
        }

        return new StatisticTableRow(complexity, points, 0, rate);
    }

    private StatisticTableRow calculateRowFailed(int complexity, int points, double multiplier) {
        double rate;
        if (data.isEmpty()) {
            rate = Config.DEFAULT_RATE;
        } else {
            rate = getLast().getRate();
        }

        double delta = points * multiplier; //* ((rate + Config.MAX_RATE) / Config.MAX_RATE);
        rate -= delta;

        if(rate <= Config.MIN_RATE) {
            rate = Config.MIN_RATE;
        }
        return new StatisticTableRow(complexity, 0, delta, rate);
    }

    /*private StatisticTableRow calculateRowCompleted(int complexity, int points, double multiplier) {
        int prevTotal;
        double prevMul;
        if(data.isEmpty()) {
            prevTotal = 0;
            prevMul = 0;
        } else {
            prevTotal = data.getLast().getTotal();
            prevMul = data.getLast().getMultiplier();
        }

        int newTotal = (int) ((prevTotal + points));
        double newMul = prevMul + multiplier;
        double rate = newTotal / newMul;

        if(rate > MAX_RATE) {
            rate = MAX_RATE;
        }

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
    }*/


    private Complexity getNextComplexity() {
        double rate = getLast().getRate();
        return Complexity.get(rate);
    }

    public double getRaring() {
        if (data.isEmpty()) {
            return Config.DEFAULT_RATE;
        } else {
            return getLast().getRate();
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
            System.out.println(table.getLast().toString2());
            //System.out.println(table.getStats());
            table.addQuestion(getRandomBoolean());

            //table.addQuestion(false, table.getNextComplexity());
            //table.addQuestion(false);
        }


    }

    private int getComplexityCount(int complexity) {
        int count = 0;
        for (StatisticTableRow row : data) {
            if(row.getComplexity() == complexity) {
                count++;
            }
        }
        return count;
    }

    public String getStats() {
        if (data.isEmpty()) {
            return "No Stats";
        }
        return String.format("Questions: %s, Correct: %s, Rating %.0f, Simple: %s, Medium: %s, Hard %s",
                    data.size(),
                    correct,
                    getLast().getRate(),
                    getComplexityCount(1),
                    getComplexityCount(2),
                    getComplexityCount(3)
                    );
    }

    private StatisticTableRow getLast() {
        return data.get(data.size() - 1);
    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result
    }
}
