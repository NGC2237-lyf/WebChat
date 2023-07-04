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
@SpringBootTest(classes = {WebChatApplication.class})
public class WebChatTest {
    @Test
    public void test() {
        System.out.println(DateUtil.getMillis());
    }
}
