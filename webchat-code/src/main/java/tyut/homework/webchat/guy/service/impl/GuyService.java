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
    public UserGuy guyList(int account) {
        return guyMapper.guyList(account);
    }

    @Override
    public List<User> guySearch(User user) {
        return guyMapper.guySearch(user);
    }

    @Override
    public boolean guyAdd(UserGuy userGuy) {
        guyMapper.guyAdd(userGuy);
        return false;
    }

    @Override
    public boolean guyDelete(String myName,String guyName) {
        guyMapper.guyDelete(1,1);
        return false;
    }

    @Override
    public boolean guyRemarkUpdate(String remark, UserGuy userGuy) {
        guyMapper.guyRemarkUpdate(remark,userGuy);
        return false;
    }

    @Override
    public User guyDetail(UserGuy userGuy) {
        return guyMapper.guyInfo(userGuy);
    }
}
