package tyut.homework.webchat.guy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.guy.dto.UserGuyDTO;
import tyut.homework.webchat.guy.service.impl.GuyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public Result guySearch(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        String id = request.getParameter("id");
        try {
            user.setId(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return Result.error("输入账号格式错误");
        }
        response.setContentType("text/html;charset=utf-8");
        return Result.success(objectMapper.writeValueAsString(guyService.guySearch(user)));
    }

    @PostMapping("/delete")
    public Result guyDelete(@RequestParam("myId") int myId, @RequestParam("guyId") int guyId) {
        guyService.guyDelete(myId, guyId);
        return Result.success("删除好友成功");
    }

    @PostMapping("/add")
    public Result guyAdd(@RequestBody UserGuyDTO userGuy) {
        if (guyService.guyAdd(userGuy)) {
            Result.success("好友添加成功");
        }
        return Result.error("该好友已经存在您的好友列表");
    }

    @PostMapping("/remark")
    public Result guyRemarkUpdate(@RequestParam("remark") String remark, @RequestBody UserGuyDTO userGuy) {
        if (guyService.guyRemarkUpdate(remark, userGuy)) {
            return Result.success("修改昵称成功");
        }
        return Result.error("修改昵称失败");
    }

    @PostMapping("/detail")
    public Result guyDetail(@RequestBody UserGuyDTO userGuy) {
        return Result.success(guyService.guyDetail(userGuy));
    }

    @PostMapping("/sendGuy")
    public Result sendGuyRequest(@RequestParam("myId")int myId,@Param("toId") int toId){
        return guyService.sendGuyRequest(myId,toId);
    }

    @PostMapping("/result")
    public Result resultGuy(@RequestParam("myId") int myId, @RequestParam("toId") int toId, @RequestParam("msg") String msg) {
        return guyService.sendGuyResultInfo(myId, toId, msg);
    }
}