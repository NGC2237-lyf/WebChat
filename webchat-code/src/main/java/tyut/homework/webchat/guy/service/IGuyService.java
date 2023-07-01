package tyut.homework.webchat.guy.service;

import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuy;

import java.util.List;

public interface IGuyService {
    UserGuy guyList(int account);

    List<User> guySearch(User user);

    boolean guyAdd(UserGuy userGuy);

    boolean guyDelete(int myId, int guyId);
    boolean guyRemarkUpdate(String remark,UserGuy userGuy);
    User guyDetail(UserGuy userGuy);
}
