package tyut.homework.webchat.information.utils;

import com.mysql.cj.util.Base64Decoder;
import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * @author TokisakiKurumi
 * @className FileUtil
 * @date 2023/6/29
 * @description
 **/
public class FileUtil {
    private static DataOutputStream outputStream = null;
    public static void touchFile(String fileName,String data) {
        try {
            outputStream = new DataOutputStream(new FileOutputStream(fileName));
            byte[] bytes = Base64Utils.decodeFromString(data);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
