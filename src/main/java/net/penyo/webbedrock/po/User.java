package net.penyo.webbedrock.po;

import net.penyo.webbedrock.pojo.UserType;

/**
 * An object representing user.
 *
 * @author Penyo
 */
public class User {

    // Key
    public Integer id;

    // Required
    public String loginName;
    public String password;
    public UserType userType;

    // Optional
    public String name;

    public User() {
    }

    public User(String loginName) {
        this.loginName = loginName;
    }

    public User(String loginName, String password, UserType userType) {
        this.loginName = loginName;
        this.password = password;
        this.userType = userType;
    }
}
