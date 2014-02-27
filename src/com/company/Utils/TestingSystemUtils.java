package com.company.Utils;

import java.io.*;
import java.util.ArrayList;

/**
 * It's the static class for often used functions
 *
 * @author Dusheyko Dima
 * @version 1.91c 07.11.2013
 */
public class TestingSystemUtils {

    public static String readFromConsole() {
        BufferedReader input;
        input = new BufferedReader(new InputStreamReader(System.in));
        try {
            return input.readLine();
        } catch (IOException e) {
            getOutFromProgram("IO error (read from the console)", -1);
            return null;
        }
    }

    public static void getOutFromProgram(String finalMessage, int finalCode) {
        System.out.println(finalMessage);
        System.exit(finalCode);
    }

    public static ArrayList<String> readFile(String fileName) {
        BufferedReader contentOfFile = null;
        String lineOfFile;
        ArrayList<String> linesOfFileCollection = new ArrayList<String>();

        try {
            contentOfFile = new BufferedReader(new FileReader(fileName));
            while (((lineOfFile = contentOfFile.readLine()) != null)) {
                linesOfFileCollection.add(lineOfFile);
            }
        } catch (FileNotFoundException e) {
            getOutFromProgram("File not found : " + fileName, -1);
        } catch (IOException e) {
            getOutFromProgram("IO error from the file  : " + fileName, -1);
        } finally {
            try {
                contentOfFile.close();
            } catch (IOException e) {
                getOutFromProgram("IO error from the file  : " + fileName, -1);
            }
        }
        return linesOfFileCollection;
    }
}
