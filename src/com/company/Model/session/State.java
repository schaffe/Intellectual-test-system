package com.company.Model.session;

import com.company.Model.session.question.Question;

import java.io.Serializable;

/**
 * Class contains state of question, whether it was correct answered
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/16/13
 */
public final class State implements Serializable{
    private final Question question;
    private boolean result;

    public State(Question question, boolean result){
        this.question = question;
        this.result = result;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean getResult() {
        return result;
    }
}
