package com.company.Model.session.logic;

import java.util.*;

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
        double[] wages = new double[topicList.size()];
        int i = 0;
        for (TopicStatisticItem item : topicList) {
            wages[i++] = item.getRevertedRate();
        }
        int index = getRandomIndex(wages);
        return topicList.get(index);
    }

    public static void main(String... args) {
        List<TopicStatisticItem> topicList = new ArrayList<>();
        for(int i = 0; i <= 100; i+= 5) {
            topicList.add(new TopicStatisticItem(String.format("%s",i),i));
        }
        TopicRandomSelector selector = new TopicRandomSelector(topicList);
        List<String> asList = new LinkedList<>();
        for(int i = 0; i <= 10000; i++) {
            asList.add(selector.get().toString());
        }
        //System.out.println(Arrays.toString(array));

        Set<String> mySet = new TreeSet<>(asList);
        for(String s: mySet){

            System.out.println(s + " " + Collections.frequency(asList, s));

        }


    }

    public int getRandomIndex(double[] wages) {
        int totalSum = 0;
        for (Double item : wages) {
            totalSum += Math.round(item);
        }
        int index = rand.nextInt(totalSum);
        double sum = 0;
        int i=0;
        while(sum < index ) {
            sum = sum + wages[i++];
        }

        return i;
    }
}
