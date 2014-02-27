package com.company;

import com.company.Controller.Controller;
import com.company.Model.TestSystem;
import com.company.View.Console;

/**
 * Entry point to the program
 *
 * @author Artur Dzidzoiev
 * @version 20.11.2013
 */
public class Main {

    public static void main(String[] args) {
        try{
        Controller controller = new Controller();
        Console console = new Console();
        TestSystem testSystem = new TestSystem(controller,console);

        testSystem.start();
        } catch (OutOfMemoryError | StackOverflowError error){
            Log.save(error);
        }
    }

}
