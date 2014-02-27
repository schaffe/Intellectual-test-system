package com.company.Model.User;

import java.io.Serializable;

/**
 * Class user, part of <code>Customer Storage</code>
 * Can be saved to file
 *
 * @author Artur Dzidzoiev
 * @author Shevchuk Sergey
 * @version 20.11.2013
 */
public final class User implements Serializable{
    private final String login;
    private final String hashedPassword;

    public User(String login, String hashedPassword) {
        this.login = login;
        this.hashedPassword = hashedPassword;
    }

    public String getLogin() {
        return login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
