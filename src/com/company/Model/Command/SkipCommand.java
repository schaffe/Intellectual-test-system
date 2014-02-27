package com.company.Model.Command;

import com.company.Model.TestSystem;

/**
 * Skip current question
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public final class SkipCommand extends Command<TestSystem> {

    public SkipCommand(TestSystem object) {
        super(object);
    }

    @Override
    public void execute() {
        object.skip();
    }
}
