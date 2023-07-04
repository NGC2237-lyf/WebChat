package tyut.homework.webchat.information.utils;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author TokisakiKurumi
 * @className DateUtil
 * @date 2023/6/29
 * @description
 **/
public class DateUtil {
    public static String getNow() {
        return DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss").format(LocalDateTime.now());
    }

    public static String getMillis() {
        return String.valueOf(System.currentTimeMillis());
    }
}
