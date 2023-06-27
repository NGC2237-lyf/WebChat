package tyut.homework.webchat.information.websocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;

/**
 * @author TokisakiKurumi
 * @className ChatHandler
 * @date 2023/6/27
 * @description
 **/
@Component
public class ChatHandler implements WebSocketHandler {

    private static final HashMap<String,WebSocketSession> SESSION_POOL = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("hello");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public String getPopulation() {
        return String.valueOf(SESSION_POOL.keySet().size());
    }
}
