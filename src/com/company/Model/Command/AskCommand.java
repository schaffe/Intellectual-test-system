package com.company.Model.Command;

import com.company.Model.TestSystem;

/**
 * Exit from the program
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/15/13
 */
public final class AskCommand extends Command<TestSystem> {

    public AskCommand(TestSystem object) {
        super(object);
    }

    public void execute(){
        object.ask();
    }
}
