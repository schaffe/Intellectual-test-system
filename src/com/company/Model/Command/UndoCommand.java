package com.company.Model.Command;

import com.company.Model.TestSystem;

/**
 * Show previous question
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public final class UndoCommand extends Command<TestSystem>{

    public UndoCommand(TestSystem object) {
        super(object);
    }

    @Override
    public void execute() {
        object.undo();
    }
}
