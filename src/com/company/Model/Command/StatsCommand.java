package com.company.Model.Command;

import com.company.Model.TestSystem;

/**
 * Skip current question
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/22/13
 */
public final class StatsCommand extends Command<TestSystem> {

    public StatsCommand(TestSystem object) {
        super(object);
    }

    @Override
    public void execute() {
        object.stats();
    }
}
