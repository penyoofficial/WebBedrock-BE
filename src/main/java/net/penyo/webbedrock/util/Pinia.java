package net.penyo.webbedrock.util;

/**
 * An object which provides global storage ability.
 *
 * @author Penyo
 */
public class Pinia {

    public static final ThreadLocal<String> TOKEN = new ThreadLocal<>();
}
