package net.penyo.webbedrock.controllor;

import jakarta.validation.constraints.Pattern;
import net.penyo.util.cypher.Digester;
import net.penyo.webbedrock.po.User;
import net.penyo.webbedrock.pojo.UserType;
import net.penyo.webbedrock.service.UserService;
import net.penyo.webbedrock.util.ActionProcessor;
import net.penyo.webbedrock.util.Body;
import net.penyo.webbedrock.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * An entrance where user posts his request about himself.
 *
 * @author Penyo
 */
@RestController
@RequestMapping("/user")
public class UserController implements BaseController<User, UserService> {

    protected UserController() {
    }

    private UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserService getService() {
        return userService;
    }

    @PostMapping("/{loginName}")
    public ResponseEntity<Body> register(@PathVariable String loginName, @RequestHeader("Authorization") @Pattern(regexp = "^\\w{8,16}$") String password) {
        List<User> userList = userService.query(new User(loginName));
        if (!userList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("该登录名已被占用", null));

        return insert(new User(loginName, password, UserType.O));
    }

    @GetMapping("/{loginName}")
    public ResponseEntity<Body> login(@PathVariable String loginName, @RequestHeader("Authorization") @Pattern(regexp = "^\\w{8,16}$") String password) {
        List<User> userList = userService.query(new User(loginName));
        if (userList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Body("用户不存在", null));
        User user = userList.getFirst();
        if (!user.password.equals(Digester.sample.hash(password)))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("密码错误", null));

        return ResponseEntity.ok(new Body("登录成功", Jwt.get(loginName)));
    }

    @GetMapping("/me")
    public ResponseEntity<Body> getMe(@RequestHeader("Authorization") String token) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyLoggedInCanDo(token);
        if (barrier != null) return barrier;

        return query(new User(Jwt.read(token)), "查询成功");
    }

    @PostMapping("/su")
    public ResponseEntity<Body> insert(@RequestHeader("Authorization") String token, @RequestBody User user) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return insert(user);
    }

    @DeleteMapping("/su/{id}")
    public ResponseEntity<Body> delete(@RequestHeader("Authorization") String token, @PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return delete(Integer.parseInt(id));
    }

    @PutMapping("/su")
    public ResponseEntity<Body> update(@RequestHeader("Authorization") String token, @RequestBody User user) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return update(user);
    }

    @GetMapping("/su/{id}")
    public ResponseEntity<Body> query(@RequestHeader("Authorization") String token, @PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return query(new User(Integer.parseInt(id)), "查询成功");
    }

    @PostMapping("/su/q")
    public ResponseEntity<Body> query(@RequestHeader("Authorization") String token, @RequestBody Optional<User> user) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return query(user.orElseGet(User::new), "查询成功");
    }
}
