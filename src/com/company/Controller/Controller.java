package com.company.Controller;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interaction with user using infinite loop of reading commands
 * from keyboard
 * Contains input element using System.in
 *
 * @author Artur Dzidzoiev
 * @version 13.11.2013
 */
public class Controller implements Observable {
    private Scanner in;
    private String input;
    private ArrayList<Observer> observers;

    public Controller() {
        this.in = new Scanner(System.in);
        observers = new ArrayList<Observer>();
    }

    /**
     * Receiving commands in infinite loop
     */
    public void readNext() {
        this.input = read();
        notifyObservers();
    }

    /**
     * Reading text from the keyboard
     *
     * @return
     */
    private String read() {
        String received = in.nextLine();
        return received;
    }

    /**
     * Close System.in stream when finishing Controller
     */
    @Override
    public void finalize() throws Throwable {
        this.in.close();
        super.finalize();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.input);
        }
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);

    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}