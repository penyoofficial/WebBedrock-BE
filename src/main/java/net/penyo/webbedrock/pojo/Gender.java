package net.penyo.webbedrock.pojo;

/**
 * An object representing cat's gender.
 *
 * @author Penyo
 */
public enum Gender {

    MALE("male"), FEMALE("female");

    public final String value;

    Gender(String value) {
        this.value = value;
    }
}
