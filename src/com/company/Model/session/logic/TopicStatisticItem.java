package com.company.Model.session.logic;

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

    public double getRevertedRate() {
        double rate = TopicStatistic.MAX_RATE - topicStatistic.getRaring();
        return rate;
    }

    public Complexity addQuestion(boolean result) {
        return topicStatistic.addQuestion(result);
    }

    public String getStats() {
        return String.format("%s : %s",name,topicStatistic.getStats());
    }

    public String getName() {
        return name;
    }
}
