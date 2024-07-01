package net.penyo.webbedrock.service;

import net.penyo.webbedrock.mapper.NekoMapper;
import net.penyo.webbedrock.po.Neko;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An executor which provides data interaction about {@code Neko}.
 *
 * @author Penyo
 */
@Service
public class NekoService implements BaseService<Neko> {

    protected NekoService() {
    }

    private NekoMapper nekoMapper;

    @Autowired
    private NekoService(NekoMapper nekoMapper) {
        this.nekoMapper = nekoMapper;
    }

    @Override
    public NekoMapper getMapper() {
        return nekoMapper;
    }
}
