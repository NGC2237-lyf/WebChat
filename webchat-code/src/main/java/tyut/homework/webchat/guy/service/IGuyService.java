package tyut.homework.webchat.guy.service;

import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuyDTO;

import java.util.List;

public interface IGuyService {
    UserGuyDTO guyList(int account);

    List<User> guySearch(User user);

    boolean guyAdd(UserGuyDTO userGuy);

    boolean guyDelete(int myId, int guyId);

    boolean guyRemarkUpdate(String remark, UserGuyDTO userGuy);

    User guyDetail(UserGuyDTO userGuy);
}
