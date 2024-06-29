package net.penyo.webbedrock.service;

import net.penyo.webbedrock.mapper.NekoMapper;
import net.penyo.webbedrock.po.Neko;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An executor which provides data interaction about {@code Neko}.
 *
 * @author Penyo
 */
@Service
public class NekoService {

    private final NekoMapper nekoMapper;

    @Autowired
    private NekoService(NekoMapper nekoMapper) {
        this.nekoMapper = nekoMapper;
    }

    public boolean insert(Neko neko) {
        return nekoMapper.insert(neko) != 0;
    }

    public Boolean delete(int id) {
        return nekoMapper.delete(id) != 0;
    }

    public Boolean update(Neko neko) {
        return nekoMapper.update(neko) != 0;
    }

    public List<Neko> query(Neko neko) {
        return nekoMapper.query(neko);
    }
}
