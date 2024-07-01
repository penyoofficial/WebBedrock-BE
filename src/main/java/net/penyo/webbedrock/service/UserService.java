package net.penyo.webbedrock.service;

import net.penyo.util.cypher.Digester;
import net.penyo.webbedrock.mapper.UserMapper;
import net.penyo.webbedrock.po.User;
import net.penyo.webbedrock.pojo.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An executor which provides data interaction about {@code User}.
 *
 * @author Penyo
 */
@Service
public class UserService implements BaseService<User> {

    protected UserService() {
    }

    private UserMapper userMapper;

    @Autowired
    private UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }

    @Override
    public String insert(User obj) {
        obj.password = Digester.sample.hash(obj.password);
        return BaseService.super.insert(obj).replace("添加", "注册");
    }

    public boolean isAdmin(String loginName) {
        return query(new User(loginName)).getFirst().userType == UserType.ADMIN;
    }
}
