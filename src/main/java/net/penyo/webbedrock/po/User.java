package net.penyo.webbedrock.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import net.penyo.webbedrock.pojo.UserType;

/**
 * An object representing user.
 *
 * @author Penyo
 */
public class User {

    @NotNull
    public Integer id;

    @NotEmpty
    public String loginName;
    @JsonIgnore
    @NotEmpty
    @Pattern(regexp = "^\\w{8,16}$")
    public String password;
    @NotEmpty
    public UserType userType;

    public String name;

    public User() {
    }

    public User(int id) {
        this.id = id;
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
