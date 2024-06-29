package net.penyo.webbedrock.mapper;

import net.penyo.webbedrock.po.Neko;
import net.penyo.webbedrock.util.DynamicSql;
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
public interface NekoMapper {

    @Insert("insert nekos (name, gender, age, skin_color, father_id, mother_id) values (#{name}, #{gender}, #{age}, #{skinColor}, #{fatherId}, #{motherId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Neko neko);

    @Delete("delete from nekos where id = #{id}")
    int delete(int id);

    @Update("update nekos set name = #{name}, gender = #{gender}, age = #{age}, skin_color = #{skinColor}, father_id = #{fatherId}, mother_id = #{motherId} WHERE id = #{id}")
    int update(Neko neko);

    @SelectProvider(type = DynamicSql.class, method = "query")
    List<Neko> query(Neko neko);
}
