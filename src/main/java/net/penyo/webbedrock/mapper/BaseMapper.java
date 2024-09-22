package net.penyo.webbedrock.mapper;

import java.util.List;

/**
 * An entrance where SQL is run.
 *
 * @author Penyo
 */
public interface BaseMapper<PO> {

    int add(PO po);

    int delete(int id);

    int update(PO po);

    List<PO> search(PO po);
}
