package net.penyo.webbedrock.pojo;

/**
 * An object representing cat's skin color.
 *
 * @author Penyo
 */
public enum SkinColor {

    WHITE("white"), BLACK("black"), YELLOW("yellow"), GRAY("gray"), BROWN("brown"), ORANGE("orange"), GINGER("ginger"), TABBY("tabby"), TORTOISE_SHELL("tortoise shell"), CALICO("calico"), BLUE("blue"), CREAM("cream"), SILVER("silver"), LILAC("lilac"), FAWN("fawn");

    public final String value;

    SkinColor(String value) {
        this.value = value;
    }
}
