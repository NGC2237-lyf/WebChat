package tyut.homework.webchat.information.cache.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import tyut.homework.webchat.information.entity.Message;
import tyut.homework.webchat.information.mapper.ChatMapper;
import tyut.homework.webchat.information.utils.RedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TokisakiKurumi
 * @className ChatJob
 * @date 2023/7/4
 * @description
 **/
@Component
@Slf4j
public class ChatJob extends QuartzJobBean {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ChatMapper mapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        if (redisUtil.hasKey("chat")) {
            int count = Integer.parseInt(redisUtil.listLen("chat").toString());
            for (int i = count;i>0;i --) {
                Message message = (Message)redisUtil.listPopLeftKey("chat");
                mapper.insertRecord(message);
            }
            log.info("聊天记录已存储");
        }
    }
}
