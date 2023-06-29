package tyut.homework.webchat.guy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.guy.domain.User;
import tyut.homework.webchat.guy.mapper.IGuyMapper;
import tyut.homework.webchat.guy.service.IGuyService;

import java.util.List;

@Service
public class GuyService implements IGuyService{
    @Autowired
    IGuyMapper guyMapper;
    @Override
    public List<User> guyList(String name) {
        List<User> users = guyMapper.guyList(name);
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @Override
    public List<User> guySearch(User user) {
        return null;
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
