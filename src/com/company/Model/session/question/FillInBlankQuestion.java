package com.company.Model.session.question;

import java.util.List;

/**
 * The class contains fill-in-the-blank question, type your answer in a console
 *
 * @author Dusheyko Dima
 * @version 1.91c 07.11.2013
 */
public class FillInBlankQuestion extends MultipleChoiceQuestion {


    public FillInBlankQuestion(String question, List<Answer> answers) {
        super(question, answers);
    }

    @Override
    public String displayAnswers() {
        return "";
        //System.out.println("Type your answer to the console :");
    }

    @Override
    public boolean checkAnswer(String answer) {
        List<Answer> answers = super.getAnswers();
        String currentAnswer;
        for (Answer s : answers) {
            currentAnswer = s.getAnswer();
            if (currentAnswer.equals(answer)) {
                return true;
            }
        }
        return false;
    }
}
