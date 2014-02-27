package com.company.Controller;

/**
 * Observable knows its observers. Any number of Observer objects
 * may observe a subject provides an interface for attaching and
 * detaching Observer objects.
 * ConcreteObservable stores state of interest to ConcreteObserver
 * sends a notification to its observers when its state changes
 */
public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
