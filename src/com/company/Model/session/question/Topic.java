package com.company.Model.session.question;

import java.util.HashMap;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public class Topic {
    private HashMap<Complexity, QuestionList> complexityMap;

    public Topic(){
        complexityMap = new HashMap<>();
    }

    public void remove(Complexity complexity) {
        complexityMap.remove(complexity);
    }

    public boolean isEmpty() {
        return complexityMap.isEmpty();
    }

    public void putQuestionList(Complexity complexity ,QuestionList questionList){
        complexityMap.put(complexity, questionList);
    }

    public Question getQuestion(Complexity complexity) {
        return complexityMap.get(complexity).getQuestion();
    }
}
