package com.company.Model.User;

import com.company.Utils.Hasher;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Container for users.
 * Can be saved to file.
 * Don`t forget to save to file after changing content!
 *
 * @author Serhey Shevchuk
 * @author Artur Dzidzoiev
 * @version 20.11.2013
 */
public class CustomersStorage implements Serializable{
    private HashMap<String,User> users;

    public CustomersStorage(){
        users = new HashMap<String,User>();
    }

    /**
     * Adding new user to storage
     */
    public void addNewUser(String name, String password) {
        name = filter(name);
        User newUser = new User(name,hash(password));
        users.put(name, newUser);
    }

    /**
     * Deleting user from file by it`s name
     */
    public void deleteUser(String name) {
        if(users.containsKey(name)){
            users.remove(name);
        }
    }

    /**
     * Check whether the user is available
     */
    public boolean isUser(String name){
        name = filter(name);
        return users.containsKey(name);
    }

    /**
     * Check whether password is correct
     */
    public boolean checkPassword(String name, String password) {
        name = filter(name);
        User currentUser = users.get(name);
        String expected = currentUser.getHashedPassword();
        String actual = hash(password);

        return expected.equals(actual);
    }

    private static String filter(String string) {
        return string.toLowerCase();
    }

    private String hash(String input) {
        return Hasher.hash(input,"MD5");
    }
}
