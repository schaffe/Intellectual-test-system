package com.company.Model.session.question;

import com.company.Model.session.QuestionParam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public class QuestionLogic implements Serializable{
    private String currentTopic;
    private Complexity currentComplexity;
    private ArrayList<String> availableTopics;
    private final Complexity[] complexities;

    public QuestionLogic(Set topics) {
        availableTopics = new ArrayList(topics);
        complexities = Complexity.values();
    }

    public QuestionParam getNextParam(boolean result){
        return new QuestionParam(randomTopic(), randomComplexity());
    }

    private String randomTopic(){
        return availableTopics.get(new Random().nextInt(availableTopics.size() - 1));
    }

    private Complexity randomComplexity() {
        return complexities[new Random().nextInt(complexities.length - 1)];
    }

    private static Complexity harder(Complexity currentComplexity){
        if(currentComplexity == Complexity.hard){
            return Complexity.hard;
        }
        return Complexity.values()[currentComplexity.ordinal() + 1];
    }

    private static Complexity easier(Complexity currentComplexity){
        if(currentComplexity == Complexity.easy){
            return Complexity.easy;
        }
        return Complexity.values()[currentComplexity.ordinal() - 1];
    }
}
