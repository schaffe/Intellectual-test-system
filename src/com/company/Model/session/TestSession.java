package com.company.Model.session;

import com.company.Model.session.question.Question;
import com.company.Model.session.question.QuestionLogic;
import com.company.Model.session.question.QuestionStorage;

import java.io.Serializable;

/**
 * Session presents an interface to interact directly with question.
 * Session contains question storage, history of asked questions and a logic of
 * a question to ask.
 * To have an access to question you should operate with <code>nextQuestion()</code>
 * and <code>previousQuestion()</code>, otherwise you would use only one question.
 *
 * @author Artur Dzidzoiev
 * @version 26/11/13
 */
public class TestSession implements Serializable{
    private QuestionLogic logic;
    private transient QuestionStorage questionStorage;
    private History history;
    private Question currentQuestion;
    private String user;
    private int counter;

    public TestSession(String user) {
        loadQuestions();
        logic = new QuestionLogic(questionStorage.getTopics());
        currentQuestion = questionStorage.getRandomQuestion();
        this.user = user;
        history = new History();
        counter = 1;
    }

    /**
     *
     */
    public String askCurrent() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Question #%d\n",counter));
        builder.append(currentQuestion.getQuestionMessage());
        builder.append("\n");
        builder.append(currentQuestion.displayAnswers());
        return builder.toString();
    }

    /**
     * Checking current question and save it to history.
     * If answer string is empty, question is considered as skipped
     */
    public void checkCurrent(String answer) {
        boolean result = false;
        if(!answer.isEmpty()){
            result = currentQuestion.checkAnswer(answer);
        }
        history.push(new State(currentQuestion, result));
    }

    public void nextQuestion() {
        boolean result = history.peek().getResult();
        QuestionParam nextParam = logic.getNextParam(result);
        currentQuestion = questionStorage.getQuestion(nextParam);
        counter++;
    }

    /**
     * Sets the current question as previous popping it from history.
     * If the history is empty, question wouldn't change.
     */
    public void previousQuestion() {
        if(!history.isEmpty()) {
            currentQuestion = history.pop().getQuestion();
            counter--;
        }
    }

    public int getStats() {
        return history.getCount();
    }

    public String getUser() {
        return user;
    }

    public void loadQuestions() {
        questionStorage = new QuestionStorage();
    }

    private static final long serialVersionUID = 1000001L;
}
