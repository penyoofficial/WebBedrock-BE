package net.penyo.webbedrock.util;

/**
 * An object which packages data before being transported to front end.
 *
 * @author Penyo
 */
public record Body(String msg, Object data) {
}
