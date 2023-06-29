package tyut.homework.webchat.guy.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuy;
import tyut.homework.webchat.guy.service.impl.GuyService;

import java.util.List;

@RestController
@RequestMapping("/guy")
public class GuyController {
    @Autowired
    GuyService guyService;

    @PostMapping("/list")
    public UserGuy guyList(String name) {
        return guyService.guyList(name);
    }

    @PostMapping("/search")
    public List<User> guySearch(User user) {
        return guyService.guySearch(user);
    }

    public void guyDelete(String name) {

    }

    public void guyAdd(User user) {

    }

    public void guyDetail(String name) {

    }
}
