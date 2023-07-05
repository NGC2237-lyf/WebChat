package tyut.homework.webchat.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.information.cache.service.HistoryService;
import tyut.homework.webchat.information.entity.Message;
import tyut.homework.webchat.information.utils.RedisUtil;
import tyut.homework.webchat.information.websocket.handler.ChatHandler;

import java.util.List;

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

    @GetMapping("/history/{start}/{end}")
    public List<Message> getHistory(@PathVariable("start") int start,@PathVariable("end") int end) {
        Long count = redisUtil.listLen("history");
        if (start + end > count) {
            boolean result = historyService.getHistory(Integer.parseInt(String.valueOf(count)),end);
            if (!result) {
                return null;
            }
            return (List<Message>) redisUtil.listRange("history",0,end -1);
        } else {
            return (List<Message>) redisUtil.listRange("history",start,start + end -1);
        }
    }
}
