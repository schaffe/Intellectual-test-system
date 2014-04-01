package com.company.Model.session.logic;

import com.company.Model.session.QuestionParam;
import com.company.Model.session.question.Complexity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public class QuestionLogic implements Serializable{
    private List<TopicStatisticItem> topicStatistics;
    private TopicRandomSelector topicRandomSelector;

    public QuestionLogic(Set<String> topics) {
        topicStatistics = new ArrayList<>();
        for (String name : topics) {
            topicStatistics.add(new TopicStatisticItem(name));
        }
        topicRandomSelector = new TopicRandomSelector(topicStatistics);
    }

    private QuestionLogic(){};

    public QuestionParam getNextParam(boolean result){
        TopicStatisticItem topicItem = topicRandomSelector.get();
        Complexity complexity = topicItem.addQuestion(result);

        return new QuestionParam(topicItem.getName(), complexity);
    }

    public List<String> getStat() {
        List<String> array = new ArrayList<>();
        for(TopicStatisticItem item: topicStatistics) {
            array.add(item.getStats());
        }
        return array;
    }

    public static void main(String... args) {
        QuestionLogic logic = new QuestionLogic();
        logic.topicStatistics = new ArrayList<>();
        for(int i = 0; i <= 5; i++) {
            logic.topicStatistics.add(new TopicStatisticItem(String.format("Topic %s",i),i*20));
        }
        logic.topicRandomSelector = new TopicRandomSelector(logic.topicStatistics);

        System.out.println(logic.getStat());

        for(int i = 0; i <= 100; i+=5) {
//            System.out.printf("%s %.0f ",i , dist.function(dist.dist0, i));
//            System.out.printf("%.0f ", dist.function(dist.dist0, i));

//            System.out.printf("%.7f ", dist.function(dist.dist50, i));
//            System.out.printf("%.0f ", dist.function(dist.dist100, i));
            System.out.println();
        }
        //System.out.println(Arrays.toString(array));



    }
}
