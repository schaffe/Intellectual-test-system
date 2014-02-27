package com.company.Model.session.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public class QuestionList {
    private List<Question> questionList;

    public QuestionList() {
        questionList = new ArrayList<Question>();
    }

    public boolean isEmpty() {
        return questionList.size() == 0;
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public Question getQuestion() {
        return questionList.get(new Random().nextInt(questionList.size()-1));
    }
}
