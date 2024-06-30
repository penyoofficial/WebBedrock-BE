package net.penyo.webbedrock.util;

import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * An object which has ability to generate dynamic SQL with specified object.
 *
 * @author Penyo
 */
public abstract class DynamicSql {

    protected abstract String tableName();

    public String insert(Object obj) {
        SQL sql = new SQL();
        sql.INSERT_INTO(tableName());

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        handleFields(obj, (field, columnName) -> {
            columns.add(columnName);
            values.add(STR."#{\{field.getName()}}");
        });

        sql.VALUES(String.join(", ", columns), String.join(", ", values));
        return sql.toString();
    }

    public String delete() {
        SQL sql = new SQL();
        sql.DELETE_FROM(tableName());

        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    public String update(Object obj) {
        SQL sql = new SQL();
        sql.UPDATE(tableName());

        handleFields(obj, (field, columnName) -> {
            sql.SET(STR."\{columnName} = #{\{field.getName()}}");
        });

        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    public String query(Object obj) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(tableName());

        handleFields(obj, (field, columnName) -> {
            sql.WHERE(STR."\{columnName} = #{\{field.getName()}}");
        });

        return sql.toString();
    }

    private void handleFields(Object obj, BiConsumer<Field, String> fieldHandler) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null) {
                    StringBuilder result = new StringBuilder();
                    for (char c : field.getName().toCharArray())
                        if (Character.isUpperCase(c)) {
                            if (!result.isEmpty()) result.append('_');
                            result.append(Character.toLowerCase(c));
                        } else result.append(c);
                    String columnName = result.toString();
                    fieldHandler.accept(field, columnName);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

