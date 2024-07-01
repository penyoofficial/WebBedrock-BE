package net.penyo.webbedrock.pojo;

/**
 * An object representing time period.
 *
 * @author Penyo
 */
public enum TimePeriod {

    /**
     * 一小时
     */
    ONY_HOUR(1000 * 60 * 60),

    /**
     * 一天
     */
    ONE_DAY(ONY_HOUR.value * 24),

    /**
     * 一周
     */
    ONE_WEEK(ONE_DAY.value * 7),

    /**
     * 一月
     */
    ONE_MONTH(ONE_DAY.value * 30);

    public final int value;

    TimePeriod(int value) {
        this.value = value;
    }
}
