import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.homework.webchat.WebChatApplication;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.mapper.IGuyMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest(classes = WebChatApplication.class)
public class GuyMapper {
    @Autowired
    private IGuyMapper guyMapper;

    @Test
    public void test001(){
        FileInputStream fileInputStream = null;
        byte[] binaryData = null;
        String url = "webchat-code/src/main/resources/photo/initPhoto.jpg";
        try {
            File file = new File(url);
            fileInputStream = new FileInputStream(file);
            binaryData = new byte[(int) file.length()];
            fileInputStream.read(binaryData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        guyMapper.insertDataByEmail(new User("123","123456","514565",binaryData));
    }
    public static void main(String[] args) {

    }
}
