package tyut.homework.webchat.common.utils;

import java.util.Random;

public class ValidateCodeUtils {
    /**
     * 随机生成验证码
     * @param length 长度为4位或者6位
     * @return
     */
    public static int generateValidateCode(int length){
        int code;
        code = new Random().nextInt(999999);//生成随机数，最大为999999
        if(code < 100000){
            code = code + 100000;//保证随机数为6位数字
        }
        return code;
    }
}
