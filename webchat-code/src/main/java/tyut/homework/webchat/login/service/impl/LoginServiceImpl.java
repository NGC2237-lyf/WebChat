package tyut.homework.webchat.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.mapper.IGuyMapper;
import tyut.homework.webchat.login.Exception.LoginMsgException;
import tyut.homework.webchat.login.service.ILoginService;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IGuyMapper iGuyMapper;

    /**
     * 校验用户信息
     * @param nickName 账号
     * @param password 密码
     * @return
     */
    @Override
    public User judgeMsg(String nickName, String password) {
        //非空判断
        if (nickName == null || password == null){
            throw new LoginMsgException("请填写账号密码");
        }
        //从数据库提取数据
        User userByNickName = iGuyMapper.getUserByNickName(nickName);
        //数据库是否有数据
        if (userByNickName == null){
            throw new LoginMsgException("数据库暂无数据");
        }
        //校验密码是否正确
        if (userByNickName.getPassword().equals(password)){
            return userByNickName;
        }
        throw new LoginMsgException("密码不正确");
    }

    @Override
    public User isExistEmail(String email) {
        return iGuyMapper.findUserByEmail(email);
    }

    @Override
    public int insertDBByEmail(User user) {
        FileInputStream fileInputStream = null;
        byte[] binaryData = null;
        URL resource = getClass().getClassLoader().getResource("webchat-code/src/main/resources/photo/initPhoto.jpg");
        try {
            File file = new File(resource.toURI());
            fileInputStream = new FileInputStream(file);
            binaryData = new byte[(int) file.length()];
            fileInputStream.read(binaryData);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //设置默认头像
        user.setPhoto(binaryData);
        iGuyMapper.insertDataByEmail(user);
        return 0;
    }
}
