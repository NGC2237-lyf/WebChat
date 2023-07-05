package tyut.homework.webchat.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.mapper.IGuyMapper;
import tyut.homework.webchat.login.Exception.LoginMsgException;
import tyut.homework.webchat.login.service.ILoginService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IGuyMapper iGuyMapper;

    /**
     * 校验用户信息
     * @param id 账号
     * @param password 密码
     * @return
     */
    @Override
    public User judgeMsg(String id, String password) {
        //非空判断
        if (id == null || password == null){
            throw new LoginMsgException("请填写正确的账号密码");
        }
        //从数据库提取数据
        User userById = iGuyMapper.getUserById(id);
        //数据库是否有数据
        if (userById == null){
            throw new LoginMsgException("数据库暂无数据");
        }
        //校验密码是否正确
        if (userById.getPassword().equals(password)){
            return userById;
        }
        throw new LoginMsgException("密码不正确");
    }

    @Override
    public User isExistEmail(String email) {
        return iGuyMapper.getUserByEmail(email);
    }


    @Override
    public int insertDBByEmail(User user) {
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
        //设置默认头像
        user.setPhoto(binaryData);
        iGuyMapper.insertDataByEmail(user);
        User userByEmail = iGuyMapper.getUserByEmail(user.getEmail());
        return userByEmail.getId();
    }
}
