package net.penyo.webbedrock.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import net.penyo.webbedrock.pojo.TimePeriod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * An object which provides json web token for each user request.
 *
 * @author Penyo
 */
@Component
public class Jwt {

    private static String secret;

    @Value("${jwt.secret}")
    private void injectSecret(String secret) {
        Jwt.secret = secret;
    }

    public static String get(String loginName) {
        return get(loginName, TimePeriod.ONE_WEEK);
    }

    public static String get(String loginName, TimePeriod timePeriod) {
        return JWT.create().withClaim("loginName", loginName).withExpiresAt(new Date(System.currentTimeMillis() + timePeriod.value)).sign(Algorithm.HMAC256(secret));
    }

    public static String read(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaim("loginName").asString();
        } catch (Exception _) {
            return null;
        }
    }
}
