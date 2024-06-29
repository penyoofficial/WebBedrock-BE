package net.penyo.webbedrock.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * An object which packages data before being transported to front end.
 *
 * @author Penyo
 */
public final class ResponseJson {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ResponseEntity<String> ok(Object data) {
        String json = "";
        try {
            json = MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException _) {
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    public static ResponseEntity<String> fail(HttpStatus reason) {
        return new ResponseEntity<>(null, reason);
    }
}
