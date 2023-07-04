package tyut.homework.webchat.information.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.information.cache.service.HistoryService;
import tyut.homework.webchat.information.mapper.ChatMapper;
import tyut.homework.webchat.information.utils.RedisUtil;

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
    public boolean getHistory(int num) {
        List<Object> record = mapper.getRecord(num);
        Long history = redisUtil.listRightPushAll("history", record);
        return history != 0;
    }
}
