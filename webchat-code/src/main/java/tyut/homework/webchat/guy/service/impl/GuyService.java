package tyut.homework.webchat.guy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.guy.dto.UserGuyDTO;
import tyut.homework.webchat.guy.mapper.IGuyMapper;
import tyut.homework.webchat.guy.service.IGuyService;

import java.util.List;

@Service
public class GuyService implements IGuyService {
    @Autowired
    IGuyMapper guyMapper;

    @Override
    public UserGuyDTO guyList(int account) {
        UserGuyDTO userGuyDTO = guyMapper.guyList(account);
        try {
            List<User> guys = userGuyDTO.getGuys();
        }catch (NullPointerException e){
            return null;
        }
        return userGuyDTO;
    }

    @Override
    public List<User> guySearch(User user) {
        List<User> users = guyMapper.guySearch(user);
        if (users.isEmpty()) {
            return null;
        }
        return users;
    }

    @Override
    public boolean guyAdd(UserGuyDTO userGuy) {
        try {
            List<User> guys = guyList(userGuy.getMyId()).getGuys();
        } catch (NullPointerException e) {
            guyMapper.guyAdd(new UserGuyDTO(userGuy.getMyId(), userGuy.getMyId()));
        }
        if (isContainGuy(userGuy)) {
            return true;
        }
        guyMapper.guyAdd(userGuy);
        guyMapper.guyAdd(new UserGuyDTO(userGuy.getGuyId(), userGuy.getMyId()));
        return false;
    }

    @Override
    public boolean guyDelete(int myId, int guyId) {
        guyMapper.guyDelete(myId, guyId);
        guyMapper.guyDelete(guyId, myId);
        List<User> guys = guyMapper.guyList(myId).getGuys();
        if (guys.size() == 1 && guys.get(0).getId() == myId) {
            guyMapper.guyDelete(myId, myId);
        }
        return true;
    }

    @Override
    public boolean guyRemarkUpdate(String remark, UserGuyDTO userGuy) {
        guyMapper.guyRemarkUpdate(remark, userGuy);
        return true;
    }

    @Override
    public User guyDetail(UserGuyDTO userGuy) {
        return guyMapper.guyInfo(userGuy);
    }


    public boolean isContainGuy(UserGuyDTO userGuy) {
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

    public boolean addYourself(int id) {
        UserGuyDTO userGuy = new UserGuyDTO();
        userGuy.setMyId(id);
        userGuy.setGuyId(id);
        guyMapper.guyAdd(userGuy);
        return true;
    }

    public Result sendGuyRequest(int myId, int toId) {
        UserGuyDTO userGuyDTO = new UserGuyDTO(myId, toId);
        if (isContainGuy(userGuyDTO)) {
            return Result.error("该好友已经存在您的好友列表");
        }
        if (this.guyMapper.getMessage(myId, toId) != null) {
            String flag = guyMapper.getMessage(myId, toId).getFlag();
            if (flag.equals("")) {
                flag = "尚未同意";
            }
            return Result.success("好友申请已存在，对方" + flag);
        }

        guyMapper.addMessage(myId, toId);

        return Result.success("发送成功");
    }

    public Result sendGuyResultInfo(int myId, int toId, String msg) {
        if (guyMapper.getMessage(myId, toId) == null) {
            return Result.error("改好友请求不存在");
        }
        guyMapper.updateMessage(myId, toId, msg);

        guyMapper.guyAdd(new UserGuyDTO(myId, toId));
        guyMapper.guyAdd(new UserGuyDTO(toId, myId));
        return Result.success("添加好友成功");
    }
}
