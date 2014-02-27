package com.company;

import com.company.Model.session.question.Answer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the AnswerTest class
 *
 * @author Dusheyko Dima
 * @version 1.91c 07.11.2013
 */
public class AnswerTest {
    public Answer testAnswer;

    @Before
    public void setUp() {
        testAnswer = new Answer();
        testAnswer.setAnswer("IsOk");
        testAnswer.setCorrectness(true);
    }

    @Test
    public void testGetAnswer() throws Exception {
        assertEquals("IsOk", testAnswer.getAnswer());
    }

    @Test
    public void testIsCorrect() throws Exception {
        assertTrue(testAnswer.isCorrect());

    }
}
