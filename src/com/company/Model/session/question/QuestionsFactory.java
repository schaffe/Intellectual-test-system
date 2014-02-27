package com.company.Model.session.question;

import java.util.List;

/**
 * The Factory is one of patterns.
 * Users of an Abstract Factory can create families of related objects without any knowledge of their concrete classes.
 *
 * @author Dusheyko Dima
 * @version 1.91c 20.10.2013
 */
public abstract class QuestionsFactory {

    public static Question factory(String type, String question, List<Answer> answers) {
        if (type.equals("MultipleChoiceQuestion")) {
            return new MultipleChoiceQuestion(question, answers);
        }
        if (type.equals("FillInBlankQuestion")) {
            return new FillInBlankQuestion(question, answers);
        }
        throw new RuntimeException("Bad Question creation: " + type);
    }
}


