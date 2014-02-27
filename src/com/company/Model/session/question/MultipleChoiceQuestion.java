package com.company.Model.session.question;

import java.util.List;

/**
 * The class contains a multiple answer
 *
 * @author Dusheyko Dima
 * @version 1.91c 07.11.2013
 */
public class MultipleChoiceQuestion extends Question {

    private String question;
    private List<Answer> answers;


    public MultipleChoiceQuestion(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    @Override
    public String getQuestionMessage() {
        return this.question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String displayAnswers() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Answer s : answers) {
            builder.append((String.format("%d ) %s%n", i++, s.getAnswer())));
        }
        return builder.toString();
    }

      @Override
    public boolean checkAnswer(String answer) {
        int variantOfQuestion;
        try {
            variantOfQuestion = Integer.parseInt(answer);
            return variantOfQuestion > answers.size() - 1 ? false : answers.get(variantOfQuestion).isCorrect();
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
