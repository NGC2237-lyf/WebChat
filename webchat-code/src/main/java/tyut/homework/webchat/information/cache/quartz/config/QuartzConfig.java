package tyut.homework.webchat.information.cache.quartz.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tyut.homework.webchat.information.cache.quartz.job.ChatJob;

/**
 * @author TokisakiKurumi
 * @className QuartzConfig
 * @date 2023/7/4
 * @description
 **/
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(ChatJob.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger() {
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(builder).build();
    }
}
