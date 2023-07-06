package tyut.homework.webchat.information.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.mapper.IGuyMapper;
import tyut.homework.webchat.information.entity.DataType;
import tyut.homework.webchat.information.entity.Message;
import tyut.homework.webchat.information.entity.MessageType;
import tyut.homework.webchat.information.entity.ServerMessage;
import tyut.homework.webchat.information.utils.DateUtil;
import tyut.homework.webchat.information.utils.FileUtil;
import tyut.homework.webchat.information.utils.RedisUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author TokisakiKurumi
 * @className ChatHandler
 * @date 2023/6/27
 * @description
 **/
@Component
@Slf4j
public class ChatHandler implements WebSocketHandler {

    public static Long count = 0L;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IGuyMapper guyMapper;

    private static final ConcurrentMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

    public static final HashMap<String,Long> recordMap = new HashMap<>();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(20,50,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        recordMap.put(userId, redisUtil.listLen("history"));
        log.info("userId: " + userId + " 建立连接");
        SESSION_POOL.put(userId, session);
        sendJoinMessage(userId);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        Message userMessage = objectMapper.readValue(message.getPayload().toString(), Message.class);
        switch (userMessage.getMessageType()) {
            case SINGLE:
                poolExecutor.execute(() -> {
                    boolean singleResult = sendSingleMessage(userMessage, getUserId(session));
                    if (!singleResult) {
                        try {
                            sendErrorMessage(session);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case BROADCAST:
                poolExecutor.execute(() -> {
                    boolean broadcastResult = sendBroadcastMessage(userMessage, getUserId(session));
                    if (!broadcastResult) {
                        try {
                            sendErrorMessage(session);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            default:
                sendErrorMessage(session);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error(exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String userId = getUserId(session);
        log.info("userId: " + userId + " 退出连接");
        SESSION_POOL.remove(userId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }



    public String getUserId(WebSocketSession session) {
        return (String) session.getAttributes().get("userId");
    }

    public boolean sendSingleMessage(Message userMessage, String userId) {
        WebSocketSession userSession = SESSION_POOL.get(userMessage.getOppositeId());
        if (Objects.nonNull(userSession) && userSession.isOpen()) {
            try {
                if (Objects.equals(userMessage.getDataType(), DataType.TEXT)) {
                    //redisUtil.listRightPush(userId+"-"+userMessage.getOppositeId()+"-chat",userMessage);
                    synchronized (userSession) {
                        userSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(userMessage)));
                    }
                    synchronized (SESSION_POOL.get(userId)) {
                        SESSION_POOL.get(userId).sendMessage(new TextMessage(objectMapper.writeValueAsString(userMessage)));
                    }
                    return true;
                } else if (Objects.equals(userMessage.getDataType(), DataType.FILE)) {
                    String fileName = "file/single/" + userId+ "/" + userMessage.getFileName();
                    String name = FileUtil.touchFile(fileName, userMessage.getInfo());
                    userMessage.setFileName(name);
                    synchronized (userSession) {
                        userSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(userMessage)));
                    }
                    synchronized (SESSION_POOL.get(userId)) {
                        SESSION_POOL.get(userId).sendMessage(new TextMessage(objectMapper.writeValueAsString(userMessage)));
                    }
                    //redisUtil.listRightPush(userId+"-"+userMessage.getUserId()+"-chat",userMessage);
                    return true;
                }
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean sendBroadcastMessage(Message userMessage, String userId) {
        boolean flag = false;
        if (Objects.equals(userMessage.getDataType(), DataType.FILE)) {
            String fileName = "file/broadcast/" + userId+ "/" + userMessage.getFileName();
            String name = FileUtil.touchFile(fileName, userMessage.getInfo());
            userMessage.setFileName(name);
            for (WebSocketSession sessions : SESSION_POOL.values()) {
                if (sessions.isOpen()) {
                    try {
                        synchronized (sessions) {
                            sessions.sendMessage(new TextMessage(objectMapper.writeValueAsString(userMessage)));
                        }
                        flag = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (flag) {
                userMessage.setInfo(null);
                redisUtil.listRightPush("chat",userMessage);
                redisUtil.listRightPush("history",userMessage);
            }
            return flag;
        } else {
            for (WebSocketSession sessions : SESSION_POOL.values()) {
                if (sessions.isOpen()) {
                    try {
                        synchronized (sessions) {
                            sessions.sendMessage(new TextMessage(objectMapper.writeValueAsString(userMessage)));
                        }
                        flag = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (flag) {
                redisUtil.listRightPush("chat",userMessage);
                redisUtil.listRightPush("history",userMessage);
            }
        }
        return flag;
    }

    public void sendErrorMessage(WebSocketSession session) throws IOException {
        synchronized (session) {
            session.sendMessage(new TextMessage(objectMapper
                    .writeValueAsString(new ServerMessage(MessageType.SINGLE,DataType.ERROR,"发送消息失败"))));
        }
    }

    public void sendJoinMessage(String userId) throws IOException {
        User user = new User();
        user.setId(Integer.parseInt(userId));
        for (WebSocketSession session : SESSION_POOL.values()) {
            if (session.isOpen()) {
                synchronized (session) {
                    session.sendMessage(new TextMessage(objectMapper
                            .writeValueAsString(new ServerMessage(MessageType.BROADCAST,DataType.JOIN,guyMapper.guySearch(user).get(0).getNickName() + " 已上线"))));
                }
            }
        }
    }

    @PostConstruct
    public void sendSessionMessage() {
        poolExecutor.execute(() -> {
            int count = SESSION_POOL.size();
            while (true) {
                if (!Objects.equals(SESSION_POOL.size(),count)) {
                    count = SESSION_POOL.size();
                    Set<String> idSet = SESSION_POOL.keySet();
                    User user = new User();
                    List<User> userList = new ArrayList<>();
                    for (String s : idSet) {
                        user.setId(Integer.parseInt(s));
                        userList.add(guyMapper.guySearch(user).get(0));
                    }
                    for (WebSocketSession session : SESSION_POOL.values()) {
                        if (session.isOpen()) {
                            try {
                                synchronized (session) {
                                    session.sendMessage(new TextMessage(objectMapper
                                            .writeValueAsString(new ServerMessage(MessageType.BROADCAST,DataType.UPDATE, objectMapper.writeValueAsString(userList)))));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

    }
}
