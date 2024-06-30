package net.penyo.webbedrock.mapper;

import net.penyo.webbedrock.mapper.sql.UserSql;
import net.penyo.webbedrock.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An entrance where SQL about user is run.
 *
 * @author Penyo
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @InsertProvider(type = UserSql.class, method = "insert")
    int insert(User obj);

    @DeleteProvider(type = UserSql.class, method = "delete")
    int delete(int id);

    @UpdateProvider(type = UserSql.class, method = "update")
    int update(User obj);

    @SelectProvider(type = UserSql.class, method = "query")
    List<User> query(User obj);
}
