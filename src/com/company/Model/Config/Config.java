package com.company.Model.Config;

import java.util.ArrayList;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 11/27/13
 */
public class Config {


    public final static int MAX_RATE = 100;
    public final static int MIN_RATE = 0;
    public final static int DEFAULT_RATE = 40;

    public static ArrayList<String> getQuestionFileNames() {
        ArrayList<String> names = new ArrayList<>();
        //names.add(JAVA_CORE_QUESTIONS);
        //names.add(JAVA_EE_QUESTIONS);
        //names.add(JAVA_SE_QUESTIONS);
        names.add(STOHAST);
        return names;
    }

    public static String getFilename(String base) {
        return String.format("%s.dat",base);
    }

    private final static String JAVA_CORE_QUESTIONS = "JavaCoreQuestions.xml";
    private final static String JAVA_SE_QUESTIONS = "JavaSEQuestions.xml";
    private final static String JAVA_EE_QUESTIONS = "JavaEEQuestions.xml";
    private final static String STOHAST = "Стохастические системы.xml";

}
