package tyut.homework.webchat.information.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
import java.util.Objects;

/**
 * @author TokisakiKurumi
 * @className WebSocketInterceptor
 * @date 2023/6/27
 * @description
 **/
@Slf4j
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            String header = servletRequest.getServletRequest().getHeader("Sec-Websocket-Protocol");
            if (Objects.isNull(header) || !Objects.equals("chat",header)) {
                return false;
            }
            log.info((request.getRemoteAddress() + " 通过拦截器"));
            String userId = servletRequest.getURI().toString().split("\\?userId=")[1];
            attributes.put("userId",userId);
            response.getHeaders().add("Sec-Websocket-Protocol","chat");
            return super.beforeHandshake(servletRequest, response, wsHandler, attributes);
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
