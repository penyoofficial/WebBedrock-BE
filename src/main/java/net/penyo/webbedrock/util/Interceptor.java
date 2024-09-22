package net.penyo.webbedrock.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

/**
 * An object which monitors request and response on permission.
 *
 * @author Penyo
 */
@Configuration
public class Interceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                Pinia.TOKEN.set(request.getHeader("Authorization"));
                if (Jwt.read(Pinia.TOKEN.get()) == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    try {
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json");
                        response.getWriter().println(STR."{\"msg\":\"拒绝执行！因为您尚未登陆或登录失效\",\"data\":null}");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                Pinia.TOKEN.remove();
            }
        }).excludePathPatterns("/user/new/*");
    }
}
