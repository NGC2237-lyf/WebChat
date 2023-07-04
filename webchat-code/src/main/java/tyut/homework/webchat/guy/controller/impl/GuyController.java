package tyut.homework.webchat.guy.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.guy.dto.UserGuy;
import tyut.homework.webchat.guy.service.impl.GuyService;

@RestController
@RequestMapping("/guy")
public class GuyController {
    @Autowired
    GuyService guyService;

    @PostMapping("/list")
    public Result guyList(@RequestParam("account") int account) {
        return Result.success(guyService.guyList(account));
    }

    @PostMapping("/search")
    public Result guySearch(@RequestBody User user) {
        return Result.success(guyService.guySearch(user));
    }

    @PostMapping("/delete")
    public Result guyDelete(@RequestParam("myId") int myId, @RequestParam("guyId") int guyId) {
        guyService.guyDelete(myId, guyId);
        return Result.success("删除好友成功");
    }

    @PostMapping("/add")
    public Result guyAdd(@RequestBody UserGuy userGuy) {
        if (guyService.guyAdd(userGuy)) {
            Result.success("好友添加成功");
        }
        return Result.error("该好友已经存在您的好友列表");
    }

    @PostMapping("/remark")
    public Result guyRemarkUpdate(@RequestParam("remark") String remark, @RequestBody UserGuy userGuy) {
        if (guyService.guyRemarkUpdate(remark, userGuy)) {
            return Result.success("修改昵称成功");
        }
        return Result.error("修改昵称失败");
    }

    @PostMapping("/detail")
    public Result guyDetail(@RequestBody UserGuy userGuy) {
        return Result.success(guyService.guyDetail(userGuy));
    }

}
