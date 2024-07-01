package net.penyo.webbedrock.util;

import net.penyo.webbedrock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * An object which provides simple packaging solutions for actions.
 *
 * @author Penyo
 */
@RestControllerAdvice
public class ActionProcessor {

    protected ActionProcessor() {
    }

    private static UserService userService;

    @Autowired
    private ActionProcessor(UserService userService) {
        ActionProcessor.userService = userService;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Body> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("拒绝执行！因为未知的错误", null));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Body> handleMissingRequestHeaderException() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("拒绝执行！因为缺失凭证", null));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Body> handleHandlerMethodValidationException() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Body("拒绝执行！因为请求数据格式非法", null));
    }
}
