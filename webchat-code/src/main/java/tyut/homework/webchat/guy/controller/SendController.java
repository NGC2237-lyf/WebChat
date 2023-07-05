package tyut.homework.webchat.guy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.homework.webchat.guy.websocket.WebSocket;

@RestController
@RequestMapping("/send")
public class SendController {
    @Autowired
    private WebSocket webSocket;

    @PostMapping("jin")
    public void jin() throws InterruptedException {
        String msg = "";
        int a = 0;
        for (int i = 0; i < 100; i++) {
            msg = String.valueOf(a);
            Thread.sleep(1000);
            webSocket.sendMessage(msg);
            a = a + 1;
        }
    }
}
