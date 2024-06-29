package net.penyo.webbedrock.util;

import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

/**
 * An object which has ability to generate dynamic SQL with specified object.
 *
 * @author Penyo
 */
public class DynamicSql {

    public String query(Object obj) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(STR."\{toUnderscoreCase(obj.getClass().getSimpleName())}s");

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null)
                    sql.WHERE(STR."\{toUnderscoreCase(field.getName())} = #{\{field.getName()}}");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return sql.toString();
    }

    private String toUnderscoreCase(String camelCase) {
        StringBuilder result = new StringBuilder();
        for (char c : camelCase.toCharArray())
            if (Character.isUpperCase(c))
                result.append('_').append(Character.toLowerCase(c));
            else result.append(c);
        if (result.charAt(0) == '_') result.deleteCharAt(0);
        return result.toString();
    }
}
