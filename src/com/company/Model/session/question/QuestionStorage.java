package com.company.Model.session.question;

import com.company.Model.Config.Config;
import com.company.Model.session.Parser.ParsingExecutor;
import com.company.Model.session.QuestionParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Class returns a question depends on required topic and
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public class QuestionStorage {
    private HashMap<String, Topic> topicMap;

    public QuestionStorage() {
        topicMap = new HashMap<>();
        load();
    }

    public Question getQuestion(QuestionParam param) {
        return topicMap.get(param.getTopic()).getQuestion(param.getComplexity());
    }

    public Set getTopics() {
        return topicMap.keySet();
    }

    public Question getRandomQuestion() {
        int random = new Random().nextInt(getTopics().size());
        ArrayList<String> arrayWithSet = new ArrayList(topicMap.keySet());
        return topicMap.get(arrayWithSet.get(random)).getQuestion(Complexity.medium);
    }

    private void load() {
        ArrayList<String> filenames = Config.getQuestionFileNames();
        for (String filename : filenames) {
            ParsingExecutor parsingExecutor = new ParsingExecutor(filename);
            Topic currentTopic = parsingExecutor.getTopic();
            if (!currentTopic.isEmpty()) {
                topicMap.put(filename, currentTopic);
            }
        }
    }

}