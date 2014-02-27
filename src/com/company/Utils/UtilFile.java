package com.company.Utils;

import java.io.*;
import java.util.LinkedList;

/**
 * Write to file
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/17/13
 */
public class UtilFile {
    private final String filename;
    private File file;

    public UtilFile(String filename){
        this.filename = filename;
        file = new File(filename);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String data) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            writer.println(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

