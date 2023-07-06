package tyut.homework.webchat.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.information.cache.service.HistoryService;
import tyut.homework.webchat.information.utils.RedisUtil;
import tyut.homework.webchat.information.websocket.handler.ChatHandler;

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
            boolean result = historyService.getHistory(userId, Integer.parseInt(String.valueOf(userCount)), end);
            if (start < userCount&&result) {
                userCount = ChatHandler.recordMap.get(userId);
                re.setData(redisUtil.listRange("history",start,userCount - 1));
                return re;
            } else if (!result) {
                return re;
            }
            re.setData(redisUtil.listRange("history",start,end -1));
            return re;
        } else {
            re.setData(redisUtil.listRange("history",userCount -(start + end), userCount - start - 1));
            return re;
        }
    }
}
