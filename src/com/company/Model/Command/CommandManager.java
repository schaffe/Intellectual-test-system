package com.company.Model.Command;

import com.company.Controller.Controller;
import com.company.Controller.Observer;
import com.company.Model.TestSystem;

/**
 * Executing commands by instant call, executing <code>n</code> commands after updating.
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/18/13
 */
public class CommandManager implements Observer{
    private final CommandFactory commandFactory;
    private Controller controller;

    public CommandManager(TestSystem testSystem, Controller controller) {
        commandFactory = new CommandFactory(testSystem);
        this.controller = controller;
    }

    private void executeImmediate(Command... commands){
         for(Command currentCommand: commands) {
             currentCommand.execute();
         }
    }

    private void interpretInput(String input) {
        if (input.startsWith("-")) {
            input = input.substring(1, input.length());
            if (isCommand(input)) {
                Command currentCommand = commandFactory.get(input);
                if (isAvailable(currentCommand)) {
                    executeImmediate(currentCommand);
                }
            } else {
                System.out.println("Not a command.");
                controller.readNext();
            }
        }
    }

    private boolean isCommand(String input) {
        return commandFactory.isCached(input);
    }

    //TODO прописать список допустимых команд для ввода, метод управления
    private boolean isAvailable(Command currentCommand) {
        return true;
    }

    @Override
    public void update(String text) {
        interpretInput(text);
    }
}
