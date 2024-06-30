package net.penyo.webbedrock.mapper.sql;

import net.penyo.webbedrock.util.DynamicSql;

/**
 * An object which generates dynamic SQL about cat.
 *
 * @author Penyo
 */
public class NekoSql extends DynamicSql {

    @Override
    protected String tableName() {
        return "nekos";
    }
}
