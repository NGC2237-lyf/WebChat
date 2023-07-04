package tyut.homework.webchat.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.information.cache.service.HistoryService;
import tyut.homework.webchat.information.entity.Message;
import tyut.homework.webchat.information.utils.RedisUtil;
import tyut.homework.webchat.information.websocket.handler.ChatHandler;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/population")
    public Set<String> getPopulation() {
        return chatHandler.getPopulation();
    }

    @PostMapping("/history/{num}")
    public List<Message> getHistory(@PathVariable("num") int num) {
        boolean result = historyService.getHistory(num);
        if (result) {
            return (List<Message>) redisUtil.listRange("history",0,redisUtil.listLen("history"));
        }
        return null;
    }
}
