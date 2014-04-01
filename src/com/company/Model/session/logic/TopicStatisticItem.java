package com.company.Model.session.logic;

import com.company.Model.Config.Config;
import com.company.Model.session.question.Complexity;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 3/24/14
 */
final class TopicStatisticItem {
    private final TopicStatistic topicStatistic;
    private final String name;

    TopicStatisticItem(String name) {
        topicStatistic = new TopicStatistic();
        this.name = name;
    }


    TopicStatisticItem(String name, double rate) {
        topicStatistic = new TopicStatistic(rate);
        this.name = name;
    }

    public double getRevertedRate() {
        double rate = Config.MAX_RATE - topicStatistic.getRaring();
        return rate;
    }

    public Complexity addQuestion(boolean result) {
        return topicStatistic.addQuestion(result);
    }

    public String getStats() {
        return String.format("%s : %s",name,topicStatistic.getStats());
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
