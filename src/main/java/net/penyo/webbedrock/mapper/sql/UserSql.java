package net.penyo.webbedrock.mapper.sql;

import net.penyo.webbedrock.util.DynamicSql;

/**
 * An object which generates dynamic SQL about user.
 *
 * @author Penyo
 */
public class UserSql extends DynamicSql {

    @Override
    protected String tableName() {
        return "users";
    }
}
