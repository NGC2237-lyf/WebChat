package tyut.homework.webchat.guy.service;

import tyut.homework.webchat.guy.domain.User;

import java.util.List;

public interface IGuyService {
    List<User> guyList(String name);

    List<User> guySearch(User user);

    boolean guyAdd(User user);

    boolean guyDelete(String name);

    User guyDetail(String name);
}
