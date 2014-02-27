package com.company.Model.Command;

import com.company.Model.TestSystem;

import java.util.HashMap;

public class CommandFactory {

    private TestSystem testSystem;
    private HashMap<String, Command> cache;

    public CommandFactory(TestSystem testSystem) {
        this.testSystem = testSystem;
        this.cache = new HashMap<String, Command>();
        loadCache();
    }

    public Command get(String key){
        Command command = cache.get(key);
        return command;
    }

    public void refresh(){
        this.cache = new HashMap<String, Command>();
        loadCache();
    }

    private boolean isCached(Command command) {
        return cache.containsValue(command);
    }

    public boolean isCached(String command) {
        return cache.containsKey(command);
    }

    private void loadCache() {
        cache.put("exit", new ExitCommand(testSystem));
        cache.put("skip", new SkipCommand(testSystem));
        cache.put("undo", new UndoCommand(testSystem));
    }
}