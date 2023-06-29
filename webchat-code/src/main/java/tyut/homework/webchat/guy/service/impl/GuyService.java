package tyut.homework.webchat.guy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuy;
import tyut.homework.webchat.guy.mapper.IGuyMapper;
import tyut.homework.webchat.guy.service.IGuyService;

import java.util.List;

@Service
public class GuyService implements IGuyService{
    @Autowired
    IGuyMapper guyMapper;
    @Override
    public UserGuy guyList(String name) {
        return guyMapper.guyList(name);
    }

    @Override
    public List<User> guySearch(User user) {
        return guyMapper.guySearch(user);
    }

    @Override
    public boolean guyAdd(User user) {
        return false;
    }

    @Override
    public boolean guyDelete(String name) {
        return false;
    }

    @Override
    public User guyDetail(String name) {
        return null;
    }
}
