package net.penyo.webbedrock.po;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import net.penyo.webbedrock.pojo.Gender;
import net.penyo.webbedrock.pojo.SkinColor;

/**
 * An object representing cat.
 *
 * @author Penyo
 */
public class Neko {

    @NotNull
    public Integer id;

    @NotEmpty
    public String name;
    @NotEmpty
    public Gender gender;
    @NotEmpty
    @Pattern(regexp = "^\\d{1,2}$")
    public Integer age;
    @NotEmpty
    public SkinColor skinColor;

    public Integer fatherId;
    public Integer motherId;

    public Neko() {
    }

    public Neko(Integer id) {
        this.id = id;
    }
}
