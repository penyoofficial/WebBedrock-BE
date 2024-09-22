package net.penyo.webbedrock.controllor;

import jakarta.validation.constraints.Pattern;
import net.penyo.util.cypher.Digester;
import net.penyo.webbedrock.po.User;
import net.penyo.webbedrock.pojo.UserType;
import net.penyo.webbedrock.service.UserService;
import net.penyo.webbedrock.util.ActionProcessor;
import net.penyo.webbedrock.util.Body;
import net.penyo.webbedrock.util.Jwt;
import net.penyo.webbedrock.util.Pinia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * An entrance where user posts his request about user.
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

    @PostMapping("/")
    public ResponseEntity<Body> add(@RequestBody @Validated User user) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.add(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Body> delete(@PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.delete(Integer.parseInt(id));
    }

    @PutMapping("/")
    public ResponseEntity<Body> update(@RequestBody @Validated User user) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.update(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Body> query(@PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.query(new User(Integer.parseInt(id)), "查询成功", "查询失败");
    }

    @GetMapping("/batch")
    public ResponseEntity<Body> search(@RequestParam User user) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.search(user);
    }

    @PostMapping("/new/{loginName}")
    public ResponseEntity<Body> register(@PathVariable String loginName, @RequestHeader("Password") @Pattern(regexp = "^\\w{8,16}$") String password) {
        List<User> userList = userService.search(new User(loginName));
        if (!userList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("该登录名已被占用", null));

        return BaseController.super.add(new User(loginName, password, UserType.O));
    }

    @GetMapping("/new/{loginName}")
    public ResponseEntity<Body> login(@PathVariable String loginName, @RequestHeader("Password") @Pattern(regexp = "^\\w{8,16}$") String password) {
        List<User> userList = userService.search(new User(loginName));
        if (userList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Body("用户不存在", null));
        User user = userList.getFirst();
        if (!user.password.equals(Digester.sample.hash(password)))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("密码错误", null));

        return ResponseEntity.ok(new Body("登录成功", Jwt.get(loginName)));
    }

    @GetMapping("/me/info")
    public ResponseEntity<Body> getInfo() {
        return BaseController.super.query(new User(Jwt.read(Pinia.TOKEN.get())), "获取成功", "获取失败");
    }
}
