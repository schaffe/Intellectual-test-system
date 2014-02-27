package com.company.Model.Command;

import com.company.Model.TestSystem;

/**
 * Exit from the program
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/15/13
 */
public final class ExitCommand extends Command<TestSystem> {

    public ExitCommand(TestSystem object) {
        super(object);
    }

    public void execute(){
        object.save();
        System.exit(0);
    }
}
