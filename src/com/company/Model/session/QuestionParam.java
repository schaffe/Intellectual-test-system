package com.company.Model.session;

import com.company.Model.session.question.Complexity;

public final class QuestionParam {
    private final String topic;
    private final Complexity complexity;

    public QuestionParam(String topic, Complexity complexity) {
        this.topic = topic;
        this.complexity = complexity;
    }

    public String getTopic() {
        return topic;
    }

    public Complexity getComplexity() {
        return complexity;
    }


}