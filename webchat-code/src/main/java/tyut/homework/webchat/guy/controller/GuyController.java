package tyut.homework.webchat.guy.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.guy.dto.UserGuyDTO;
import tyut.homework.webchat.guy.service.impl.GuyService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/guy")
public class GuyController {
    @Autowired
    GuyService guyService;

    @PostMapping("/list/{account}")
    public Result guyList(@PathVariable("account") int account) {
        return Result.success(guyService.guyList(account));
    }

    @PostMapping("/search")
    public Result guySearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream requestInputStream = request.getInputStream();
        //将字节流转换为字符流,并设置字符编码为utf-8
        InputStreamReader ir = new InputStreamReader(requestInputStream, "utf-8");
        //使用字符缓冲流进行读取
        BufferedReader br = new BufferedReader(ir);
        //开始拼装json字符串
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        JSONObject jsonObject = JSONObject.parseObject(sb.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("text/html;charset=utf-8");
        User user = new User();
        String id = String.valueOf(jsonObject.get("id"));
        try {
            user.setId(Integer.parseInt(id));
        } catch (Exception e) {
            return Result.error("输入账号格式错误");
        }
        return Result.success((Object) objectMapper.writeValueAsString((guyService.guySearch(user))));
    }

    @PostMapping("/delete")
    public Result guyDelete(@RequestParam("myId") int myId, @RequestParam("guyId") int guyId) {
        guyService.guyDelete(myId, guyId);
        return Result.success("删除好友成功");
    }

    @PostMapping("/add")
    public Result guyAdd(@RequestBody UserGuyDTO userGuy) {
        if (guyService.guyAdd(userGuy)) {
            Result.error("该好友已经存在您的好友列表");
        }
        return Result.success("好友添加成功");
    }

    @PostMapping("/remark/{remark}")
    public Result guyRemarkUpdate(@PathVariable("remark") String remark, @RequestBody UserGuyDTO userGuy) {
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
    public Result sendGuyRequest(@RequestParam("myId") int myId, @Param("toId") int toId) {
        return guyService.sendGuyRequest(myId, toId);
    }

    @PostMapping("/result")
    public Result resultGuy(@RequestParam("myId") int myId, @RequestParam("toId") int toId, @RequestParam("msg") String msg) {
        return guyService.sendGuyResultInfo(myId, toId, msg);
    }
}