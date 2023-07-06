package tyut.homework.webchat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.homework.webchat.information.utils.DateUtil;
import tyut.homework.webchat.information.utils.FileUtil;

/**
 * @author TokisakiKurumi
 * @className WebChatTest
 * @date 2023/6/29
 * @description
 **/
@SpringBootTest(classes = {WebChatApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebChatTest {
    @Test
    public void test() {
        System.out.println(FileUtil.touchFile("file/1/xcc.txt", "5aWa55Cb5Z+O"));
    }
}
