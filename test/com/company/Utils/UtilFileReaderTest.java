package com.company.Utils;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/17/13
 */
public class UtilFileReaderTest extends Assert {

    private String filename;
    private UtilFileReader fileReader;


    @Before
    public void setUp() throws Exception {
        filename = "test.txt";
        fileReader = new UtilFileReader(filename);
    }

    @Test
    public void testReadFile() throws Exception {
        //assertEquals("[How are you ? /qMultipleChoiceQuestion, 10 /a true, 28 /a false, What's your name ? /qFillInBlankQuestion, Dima /a true]",fileReader.readFile());
    }
}
