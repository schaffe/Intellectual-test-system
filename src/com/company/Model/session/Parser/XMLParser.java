package com.company.Model.session.Parser;

import com.company.Model.session.question.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class.
 * User: Serhey Shevchuk
 * Date: 20.11.13
 * Time: 2:03
 */
public class XMLParser extends DefaultHandler {
    private Topic topic;
    private String elementThatParsing;
    private Question question;
    private QuestionList easyList;
    private QuestionList mediumList;
    private QuestionList hardList;
    private String questionTitle;
    private String questionType;
    private String questionComplexity;
    private Answer answer;
    private List<Answer> answers;

    public XMLParser(){
        easyList = new QuestionList();
        mediumList = new QuestionList();
        hardList = new QuestionList();
        topic = new Topic();
        topic.putQuestionList(Complexity.easy, easyList);
        topic.putQuestionList(Complexity.medium, mediumList);
        topic.putQuestionList(Complexity.hard, hardList);
    }

    public void startElement(String uri, String localName,
                             String qName, org.xml.sax.Attributes attributes) throws SAXException {
        elementThatParsing = qName;

        if (elementThatParsing.equals("answers")) {
            answers = new ArrayList<Answer>();
        }
        if (elementThatParsing.equals("variant")) {
            answer = new Answer();
            answer.setCorrectness(convertToBoolean(attributes.getValue("correctness")));
        }
    }

    public void characters(char[] charsOfParsing, int start, int length) throws SAXException {
        if (elementThatParsing.equals("type")) {
            questionType = new String(charsOfParsing, start, length);
        }
        if (elementThatParsing.equals("title")) {
            questionTitle = new String(charsOfParsing, start, length);
        }
        if (elementThatParsing.equals("variant")) {
            answer.setAnswer(new String(charsOfParsing, start, length));
            answers.add(answer);
        }
        if(elementThatParsing.equals("complexity")){
            questionComplexity = new String(charsOfParsing, start, length);
        }

    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if(qName.equals("question")) {
            question = QuestionsFactory.factory(questionType, questionTitle, answers);
            switch(Complexity.valueOf(questionComplexity)){
                case easy:
                    easyList.addQuestion(question);
                    break;
                case medium:
                    mediumList.addQuestion(question);
                    break;
                case hard:
                    hardList.addQuestion(question);
                    break;
                default: throw new IllegalArgumentException(questionComplexity);
            }
        }
        elementThatParsing = "";
    }

    public Topic getTopic(){
        if(easyList.isEmpty()) {
            topic.remove(Complexity.easy);
        }
        if(mediumList.isEmpty()) {
            topic.remove(Complexity.medium);
        }
        if(hardList.isEmpty()) {
            topic.remove(Complexity.hard);
        }
        return topic;
    }


    private boolean convertToBoolean(String stingWithBooleanVariable) {
        return Boolean.valueOf(stingWithBooleanVariable);
    }
}
