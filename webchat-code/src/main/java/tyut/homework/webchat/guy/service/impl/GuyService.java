package tyut.homework.webchat.guy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuy;
import tyut.homework.webchat.guy.mapper.IGuyMapper;
import tyut.homework.webchat.guy.service.IGuyService;

import java.util.List;

@Service
public class GuyService implements IGuyService {
    @Autowired
    IGuyMapper guyMapper;

    @Override
    public UserGuy guyList(int account) {
        return guyMapper.guyList(account);
    }

    @Override
    public List<User> guySearch(User user) {
        List<User> users = guyMapper.guySearch(user);
        if (users.isEmpty()) {
            System.err.println("用户不存在");
            return null;
        }
        return users;
    }

    @Override
    public boolean guyAdd(UserGuy userGuy) {
        if (isContainGuy(userGuy)) {
            return false;
        }
        guyMapper.guyAdd(userGuy);
        return true;
    }

    @Override
    public boolean guyDelete(int myId, int guyId) {
        guyMapper.guyDelete(myId, guyId);
        return true;
    }

    @Override
    public boolean guyRemarkUpdate(String remark, UserGuy userGuy) {
        guyMapper.guyRemarkUpdate(remark, userGuy);
        return true;
    }

    @Override
    public User guyDetail(UserGuy userGuy) {
        return guyMapper.guyInfo(userGuy);
    }

    public boolean isContainGuy(UserGuy userGuy) {
        //查看好友列表是否还有已添加的成员
        //查看好友列表
        List<User> guys = guyMapper.guyList(userGuy.getMyId()).getGuys();
        //查看好友列表是否已经包含已搜索成员
        if (guys.contains(guyMapper.guyInfo(userGuy))) {
            System.err.println("该好友已经存在您的好友列表");
            return true;
        }
        return false;
    }

    public boolean addYourself(int id){
        UserGuy userGuy = new UserGuy();
        userGuy.setMyId(id);
        userGuy.setGuyId(id);
        guyMapper.guyAdd(userGuy);
        return true;
    }
}
