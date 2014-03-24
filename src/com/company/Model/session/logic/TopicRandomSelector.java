package com.company.Model.session.logic;

import java.util.List;
import java.util.Random;

/**
 * Selecting random topic depends on rating of topic
 * i.e. if the rating is higher, the probability of getting this topic is lower.
 *
 * @author Artur Dzidzoiev
 * @version 3/24/14
 */
class TopicRandomSelector {
    private List<TopicStatisticItem> topicList;
    private Random rand;

    TopicRandomSelector(List<TopicStatisticItem> topicList) {
        this.topicList = topicList;
        rand = new Random();

    }

    public TopicStatisticItem get() {
        int totalSum = 0;
        for (TopicStatisticItem item : topicList) {
            totalSum += item.getRevertedRate();
        }
        int index = rand.nextInt(totalSum);
        double sum = 0;
        int i=0;
        while(sum < index ) {
            sum = sum + topicList.get(i++).getRevertedRate();
        }
        return topicList.get(i-1);
    }
}
