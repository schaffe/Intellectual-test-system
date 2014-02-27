package com.company.Utils;

import com.company.Model.session.question.Answer;
import com.company.Model.session.Parser.XMLParser;
import com.company.Model.session.question.Question;
import com.company.Model.session.question.QuestionList;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Class that testing XMLParser.
 * User: Serhey Shevchuk
 * Date: 20.11.13
 * Time: 2:16
 */
public class XMLParserTest {
    XMLParser saxParser;

    @Before
    public void setUp() throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        saxParser = new XMLParser();
        parser.parse(new File("QuestionFileForTesting.xml"), saxParser);
    }

    @Test
    public void test_question_title_parsing() throws NoSuchFieldException, IllegalAccessException {
        String questionTitle = convertToString("questionTitle");
        assertEquals(questionTitle, "questionTitle");
    }

    @Test
    public void test_question_type_parsing() throws NoSuchFieldException, IllegalAccessException {
        String questionType = convertToString("questionType");
        assertEquals(questionType, "MultipleChoiceQuestion");
    }

    @Test
    public void test_answer_parsing() throws NoSuchFieldException, IllegalAccessException {
        Answer answer = (Answer) getField("answer").get(saxParser);
        assertEquals(answer.getAnswer(), "variant");
        assertEquals(answer.isCorrect(), false);
    }

    @Test
    public void test_answer_list_size() throws NoSuchFieldException, IllegalAccessException {
        List<Answer> answerList = (List) getField("answers").get(saxParser);
        assertTrue(answerList != null);
        assertTrue(answerList.size() == 3);
    }

    @Test
    public void test_complexity_parsing() throws NoSuchFieldException, IllegalAccessException {
        String questionComplexity = convertToString("questionComplexity");
        assertEquals(questionComplexity, "easy");
    }

    @Test
    public void test_question_parsing_correctness() throws NoSuchFieldException, IllegalAccessException {
        Question question = (Question) getField("question").get(saxParser);
        assertTrue(question != null);
        assertEquals(question.getQuestionMessage(), "questionTitle");
        //assertEquals(question.getComplexity(), "easy");
    }

    @Test
    public void test_check_questions_amount() throws NoSuchFieldException, IllegalAccessException {
        QuestionList questionList = (QuestionList) getField("questionList").get(saxParser);
        assertTrue(questionList != null);

    }

    private Field getField(String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = saxParser.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    private String convertToString(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return String.valueOf(getField(fieldName).get(saxParser));
    }
}
