package tyut.homework.webchat.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.information.cache.service.HistoryService;
import tyut.homework.webchat.information.entity.MessageType;
import tyut.homework.webchat.information.utils.RedisUtil;
import tyut.homework.webchat.information.websocket.handler.ChatHandler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;

/**
 * @author TokisakiKurumi
 * @className ChatController
 * @date 2023/6/27
 * @description
 **/
@RestController
@RequestMapping("/information")
public class ChatController {

    @Autowired
    private ChatHandler chatHandler;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/history/{userId}/{start}/{end}")
    public Result getHistory(@PathVariable("userId") String userId,@PathVariable("start") int start,@PathVariable("end") int end) {
        Result re = new Result();
        re.setCode(200);
        Long userCount = ChatHandler.recordMap.get(userId);
        if (start + end > userCount) {
            boolean result = historyService.getHistory(userId, Integer.parseInt(String.valueOf(userCount)) , start + end);
            userCount = ChatHandler.recordMap.get(userId);
            if (start <= userCount&&result) {
                userCount = ChatHandler.recordMap.get(userId);
                re.setData(redisUtil.listRange("history",userCount -start - end < 0 ? 0 : userCount -start -end,userCount - start - 1));
                return re;
            } else if (!result) {
                return re;
            }
            re.setData(redisUtil.listRange("history",start,start + end - 1));
            return re;
        } else {
            re.setData(redisUtil.listRange("history",userCount -(start + end), userCount - start - 1));
            return re;
        }
    }

    @PostMapping("/download/{type}/{userId}/{fileName}")
    public Result getFile(@PathVariable("type")String type, @PathVariable("userId") String userId, @PathVariable("fileName") String fileName) {
        Result result = new Result();
        File file = null;
        try {
            if (Objects.equals(type,MessageType.BROADCAST.getDescription())) {
                file = new File("file/broadcast/" + userId + "/" + fileName);
            } else {
                file = new File("file/single/" + userId + "/" + fileName);
            }
            if (!file.exists()) {
                result.setCode(500);
                return result;
            }
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
            byte[] bytes = inputStream.readAllBytes();
            result.setData(Base64Utils.encodeToString(bytes));
            result.setCode(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/cache/{userId}/{oppositeId}/{start}/{end}")
    public Result getCache(@PathVariable("userId") String userId,@PathVariable("oppositeId") String oppositeId,@PathVariable("start") int start,@PathVariable("end") int end) {
        Result result = new Result();

        return null;
    }
}
