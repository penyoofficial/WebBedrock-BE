package net.penyo.webbedrock.po;

import net.penyo.webbedrock.pojo.Gender;
import net.penyo.webbedrock.pojo.SkinColor;

/**
 * An object representing cat.
 *
 * @author Penyo
 */
public class Neko {

    // Key
    public Integer id;

    // Required
    public String name;
    public Gender gender;
    public Integer age;
    public SkinColor skinColor;

    // Optional
    public Integer fatherId;
    public Integer motherId;

    public Neko() {
    }

    public Neko(Integer id) {
        this.id = id;
    }
}
