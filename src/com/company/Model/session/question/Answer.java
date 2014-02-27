package com.company.Model.session.question;

import java.io.Serializable;

/**
 * The simple answer class
 *
 * @author Dusheyko Dima
 * @version 1.91c 07.11.2013
 */
public class Answer implements Serializable{
    private String answer;
    private boolean isTrue;

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrectness(boolean aTrue) {
        isTrue = aTrue;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isTrue;
    }

}
