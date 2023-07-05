package tyut.homework.webchat.information.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import tyut.homework.webchat.information.websocket.handler.ChatHandler;
import tyut.homework.webchat.information.websocket.interceptor.WebSocketInterceptor;

/**
 * @author TokisakiKurumi
 * @className WebSocketConfigStart
 * @date 2023/6/27
 * @description
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private static ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/chat")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }

    @Autowired
    public void setChatHandler(ChatHandler chatHandler) {
        WebSocketConfig.chatHandler = chatHandler;
    }
}
