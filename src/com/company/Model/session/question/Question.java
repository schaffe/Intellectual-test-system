package com.company.Model.session.question;

import java.io.Serializable;

/**
 * The general interface of question objects
 *
 * @author Shevchuk Sergey
 * @version 1.91c 07.11.2013
 */
public abstract class Question implements Serializable {

    abstract public String getQuestionMessage();

    abstract public String displayAnswers();

    abstract public boolean checkAnswer(String answer);


}
