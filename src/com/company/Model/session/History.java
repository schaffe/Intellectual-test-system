package com.company.Model.session;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * History implements Stack attributes.
 * A stack class implemented as a wrapper around a java.util.LinkedList.
 * All stack methods simply delegate to LinkedList methods.
 *
 * @version 16.09.2013
 */
public class History implements Serializable {
    private LinkedList<State> askedQuestions;

    public History() {
        askedQuestions = new LinkedList<State>();
    }

    public void push(State item) {
        askedQuestions.addFirst(item);
    }

    public State pop() {
        return askedQuestions.removeFirst();
    }

    public State peek() {
        return askedQuestions.getFirst();
    }

    public int size() {
        return askedQuestions.size();
    }

    public boolean isEmpty() {
        return askedQuestions.isEmpty();
    }

    public int getCount() {
        int count = 0;
        for (State state: askedQuestions){
            count += state.getResult() ? 1 : 0;
        }
        return count;
    }
}
