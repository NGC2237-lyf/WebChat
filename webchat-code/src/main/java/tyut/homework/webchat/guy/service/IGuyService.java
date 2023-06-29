package tyut.homework.webchat.guy.service;

import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuy;

import java.util.List;

public interface IGuyService {
    UserGuy guyList(String name);

    List<User> guySearch(User user);

    boolean guyAdd(User user);

    boolean guyDelete(String name);

    User guyDetail(String name);
}
