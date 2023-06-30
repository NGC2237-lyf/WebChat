package tyut.homework.webchat.guy.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public UserGuy guyList(@RequestParam("account") int account) {
        return guyService.guyList(account);
    }

    @PostMapping("/search")
    public List<User> guySearch(@RequestBody User user) {
        return guyService.guySearch(user);
    }

    @PostMapping("/delete")
    public void guyDelete(@RequestParam("myId") int myId,@RequestParam("guyId") int guyId) {
        guyService.guyDelete(myId, guyId);
    }

    @PostMapping("/add")
    public void guyAdd(@RequestBody UserGuy userGuy) {
        guyService.guyAdd(userGuy);
    }

    @PostMapping("/remark")
    public void guyRemarkUpdate(@RequestParam("remark") String remark, @RequestBody UserGuy userGuy){
        guyService.guyRemarkUpdate(remark, userGuy);
    }

    @PostMapping("/detail")
    public User guyDetail(@RequestBody UserGuy userGuy) {
        return guyService.guyDetail(userGuy);
    }
}
