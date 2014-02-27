package com.company.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Reading a file and storing data into LinkedList<String>
 *
 * @author Artur Dzidzoiev
 * @version 11/8/13
 */
public class UtilFileReader {
    private final String filename;

    public UtilFileReader(String file) {
        this.filename = file;
    }

    /**
     * Create an array of String with questions from file
     */
    public LinkedList<String> readFile() {
        BufferedReader reader = null;
        String receivedLine = "";
        LinkedList<String> received = new LinkedList<String>();

        try {
            reader = new BufferedReader(new java.io.FileReader(filename));
            while ((receivedLine = reader.readLine()) != null) {
                received.add(receivedLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return received;
    }
}