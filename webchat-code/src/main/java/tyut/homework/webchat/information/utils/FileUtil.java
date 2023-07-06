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
    public static String touchFile(String fileName,String data) {
        int count = 1;
        File file = null;
        file = new File(fileName);
        File newFile = file;
        try {
            File parent = new File(file.getParent());
            if (!parent.exists()) {
                parent.mkdirs();
            }
            StringBuilder stringBuilder = null;
            String[] split = new String[0];
            while (newFile.exists()) {
                split = file.getName().split("\\.");
                stringBuilder = new StringBuilder();
                for (int i = 0; i < split.length; i++) {
                    if (i == split.length - 1) {
                        break;
                    }
                    if (i == split.length - 2) {
                        stringBuilder.append(split[i]);
                        continue;
                    }
                    stringBuilder.append(split[i] + ".");
                }
                newFile = new File(parent.getPath() + "/" + stringBuilder + "(" + count + ")." + split[split.length - 1]);
                count++;
            }
            outputStream = new DataOutputStream(new FileOutputStream(newFile));
            byte[] bytes = Base64Utils.decodeFromString(data);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFile.getName();
    }
}
