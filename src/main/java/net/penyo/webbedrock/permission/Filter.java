package net.penyo.webbedrock.permission;

import net.penyo.webbedrock.service.UserService;
import net.penyo.webbedrock.util.Body;
import net.penyo.webbedrock.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * An object which provides simple packaging solutions.
 *
 * @author Penyo
 */
@RestControllerAdvice
public class Filter {

    protected Filter() {
    }

    private static UserService userService;

    @Autowired
    private Filter(UserService userService) {
        Filter.userService = userService;
    }

    public static ResponseEntity<Body> onlyAdminCanDo(String token) {
        if (onlyLoggedInCanDo(token) == null)
            if (!userService.isAdmin(Jwt.read(token)))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Body("拒绝执行！因为您的权限不足", null));
        return null;
    }

    public static ResponseEntity<Body> onlyLoggedInCanDo(String token) {
        if (Jwt.read(token) == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Body("拒绝执行！因为您尚未登陆或登录失效", null));
        return null;
    }

    public static ResponseEntity<Body> ifResult(String result) {
        if (result.contains("成功"))
            return ResponseEntity.ok(new Body(result, null));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Body(result, null));
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<Body> handleMissingRequestHeaderException() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("拒绝执行！因为缺失令牌", null));
    }
}
