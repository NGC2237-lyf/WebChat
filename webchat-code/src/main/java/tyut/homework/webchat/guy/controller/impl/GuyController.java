package tyut.homework.webchat.guy.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.guy.domain.User;
import tyut.homework.webchat.guy.service.impl.GuyService;

import java.util.List;

@RestController
public class GuyController {
    @Autowired
    GuyService guyService;

    @GetMapping("/list")
    @ResponseBody
    public List<User> guyList(String name) {
        return guyService.guyList(name);
    }

    public Result guySearch(String remark) {
        return null;
    }

    public void guyDelete(String name) {

    }

    public void guyAdd(User user) {

    }

    public void guyDetail(String name) {

    }
}
