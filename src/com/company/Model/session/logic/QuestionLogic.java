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


    public QuestionParam getNextParam(boolean result){
        TopicStatisticItem topicItem = topicRandomSelector.get();
        Complexity complexity = topicItem.addQuestion(result);

        return new QuestionParam(topicItem.getName(), complexity);
    }

}
