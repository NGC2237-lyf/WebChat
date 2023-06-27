package tyut.homework.webchat.information.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
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
        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String header = servletRequest.getHeader("Sec-Websocket-Protocol");
            if (Objects.isNull(header) || !Objects.equals("chat",header)) {
                return false;
            }
            log.info(((HttpServletRequest) request).getRemoteUser() + "通过拦截器");
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
