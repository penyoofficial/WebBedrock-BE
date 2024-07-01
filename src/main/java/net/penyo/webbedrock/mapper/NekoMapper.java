package net.penyo.webbedrock.mapper;

import net.penyo.webbedrock.mapper.sql.NekoSql;
import net.penyo.webbedrock.po.Neko;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An entrance where SQL about cat is run.
 *
 * @author Penyo
 */
@Repository
@Mapper
public interface NekoMapper extends BaseMapper<Neko> {

    @InsertProvider(type = NekoSql.class, method = "insert")
    int insert(Neko obj);

    @DeleteProvider(type = NekoSql.class, method = "delete")
    int delete(int id);

    @UpdateProvider(type = NekoSql.class, method = "update")
    int update(Neko obj);

    @SelectProvider(type = NekoSql.class, method = "query")
    List<Neko> query(Neko obj);
}
