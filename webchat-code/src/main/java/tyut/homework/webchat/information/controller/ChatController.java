package tyut.homework.webchat.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tyut.homework.webchat.information.websocket.handler.ChatHandler;

/**
 * @author TokisakiKurumi
 * @className ChatController
 * @date 2023/6/27
 * @description
 **/
@RestController("/information")
public class ChatController {

    @Autowired
    private ChatHandler chatHandler;

    @GetMapping("/population")
    @ResponseBody
    public String getPopulation() {
       return chatHandler.getPopulation();
    }
}
