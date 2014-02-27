package com.company.Model;

import com.company.Controller.Controller;
import com.company.Controller.Observer;
import com.company.Model.Command.CommandManager;
import com.company.Model.Config.Config;
import com.company.Model.User.CustomersStorage;
import com.company.Model.session.TestSession;
import com.company.Utils.UtilSerializableFile;
import com.company.View.Console;

/**
 * Main class for Test System.
 * Contains the logic for implementation API for whole modules.
 *
 * @author Artur Dzidzoiev
 * @version 20.11.2013
 */
public class TestSystem implements Observer {
    /**
     * Field which receives every input from controller.
     * Can be changed by executing <code>update()</code> method.
     */
    private String input;
    /**
     * View element of the system. It`s used to output strings in console.
     * Can be accessed by <code>output(Sting)</code> method.
     */
    private Console console;
    /**
     * Session for current user.
     * Updates after logging user.
     */
    private TestSession session;
    /**
     * Input element of the system.
     * Receives line from keyboard and updates all of observers.
     */
    private Controller controller;

    public TestSystem(Controller controller, Console console) {
        this.console = console;
        this.controller = controller;
        controller.registerObserver(this);
        controller.registerObserver(new CommandManager(this, controller));
    }

    @Override
    public void update(String text) {
        input = text;
    }

    public void start() {
        String user = authorize();
        loadSession(user);
        while (true) {
            ask();
        }
        //output(String.format("Test finished. Correct answers: %d", session.getStats()));
    }

    private void loadSession(String user) {
        UtilSerializableFile<TestSession> sessionFile = new UtilSerializableFile<>(Config.getFilename(user));
        if (sessionFile.exists()) {
            session = sessionFile.load();
            session.loadQuestions();
        } else {
            session = new TestSession(user);
        }
    }

    private String authorize() {
        final String filename = "users.dat";
        UtilSerializableFile<CustomersStorage> usersFile = new UtilSerializableFile<>(filename);
        CustomersStorage customersStorage = loadUsers(usersFile);
        String username = readLogin();

        if (checkName(customersStorage)) {
            authenticate(customersStorage, username);
        } else {
            createNewUser(customersStorage, username);
        }
        usersFile.save(customersStorage);
        return username;
    }

    private void createNewUser(CustomersStorage customersStorage, String username) {
        output("User not found. Creating new.");
        output("New password:");
        controller.readNext();
        customersStorage.addNewUser(username, input);
    }

    private void authenticate(CustomersStorage customersStorage, String username) {
        output("Password:");
        boolean isAuthenticated;
        do {
            controller.readNext();
            isAuthenticated = customersStorage.checkPassword(username, input);
            if (!isAuthenticated) {
                output("Incorrect password. Try again.");
            }
        } while (!isAuthenticated);
    }

    private String readLogin() {
        output("Login:");
        controller.readNext();
        return input;
    }

    private CustomersStorage loadUsers(UtilSerializableFile<CustomersStorage> usersFile) {
        CustomersStorage customersStorage;
        if (usersFile.exists()) {
            customersStorage = usersFile.load();
        } else {
            customersStorage = new CustomersStorage();
        }
        return customersStorage;
    }

    private void ask() {
        output(session.askCurrent());
        controller.readNext();
        session.checkCurrent(input);
        session.nextQuestion();
    }

    private boolean checkName(CustomersStorage customersStorage) {
        return (customersStorage.isUser(input));
    }

    private void output(String text) {
        console.println(text);
    }

    public void save() {
        UtilSerializableFile<TestSession> sessionFile = new UtilSerializableFile<>(Config.getFilename(session.getUser()));
        sessionFile.save(session);
    }

    public void skip() {
        session.checkCurrent("");
        ask();
    }

    public void undo() {
        session.previousQuestion();
        ask();
    }
}