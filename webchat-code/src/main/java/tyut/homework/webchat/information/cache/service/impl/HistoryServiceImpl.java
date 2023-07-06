package tyut.homework.webchat.information.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.information.cache.service.HistoryService;
import tyut.homework.webchat.information.mapper.ChatMapper;
import tyut.homework.webchat.information.utils.RedisUtil;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author TokisakiKurumi
 * @className HistoryServiceImpl
 * @date 2023/7/2
 * @description
 **/
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private ChatMapper mapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean getHistory(int start, int end) {
        List<Object> record = mapper.getRecord(start, end);
        if (record.size() == 0) {
            return false;
        }
        Long history = redisUtil.listLeftPushAll("history", record);

        return history != 0;
    }

    @PostConstruct
    @DependsOn("chatMapper")
    public void getHistory() throws InterruptedException {
        if (redisUtil.hasKey("history")) {
            redisUtil.delKey("history");
        }
        getHistory(0,2);
    }
}
