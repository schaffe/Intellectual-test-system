package com.company.Model.Command;

/**
 * Command declares an interface for all commands, providing a simple
 * execute() method which asks the Receiver of the command to carry
 * out an operation.
 */
public abstract class Command<T> {
    protected T object;

    public Command(T object) {
        this.object = object;
    }

    public abstract void execute();
}
