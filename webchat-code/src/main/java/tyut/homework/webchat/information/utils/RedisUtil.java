package tyut.homework.webchat.information.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author TokisakiKurumi
 * @className RedisUtil
 * @date 2023/6/30
 * @description
 **/
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void setKeyTime(String key,long time) {
        redisTemplate.expire(key,time, TimeUnit.SECONDS);
    }

    public Long getKeyTime(String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long delKey(String... key) {
        if (Objects.isNull(key) || key.length < 1) {
            return 0L;
        }
        return redisTemplate.delete(Arrays.asList(key));
    }

    public String keyType(String key) {
        DataType dataType = redisTemplate.type(key);
        assert Objects.nonNull(dataType);
        return dataType.code();
    }

    public void batchSet(Map<String,Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public List<Object> batchGet(List<String> list) {
        return redisTemplate.opsForValue().multiGet(list);
    }

    public Object objectGetKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void objectSetValue(String key,Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public Long listLeftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public Long listRightPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Long listLeftPushAll(String key, List<Object> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    public Long listRightPushAll(String key, List<Object> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    public void listIndexSet(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    public Object listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public Object listPopLeftKey(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public Object listPopRightKey(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public Long listLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public Object listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public Long listRem(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    public void listTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
}
