package com.company.Controller;

/**
 * Observer  (IInvestor) defines an updating interface for objects
 * that should be notified of changes in a subject.
 * ConcreteObserver  (Investor) maintains a reference to a ConcreteSubject
 * object stores state that should stay consistent with the subject's
 * implements the Observer updating interface to keep its state consistent with the subject's
 */
public interface Observer {
    /**
     * Метод вызывается, когда происходят какие - то изменения
     * в наблюдаемом объекте, а именно наблюдаемый объект вызовет
     * notifyObservers, чтобы все наблюдатели
     * узнали об изменении.
     */
    void update(String text);
}