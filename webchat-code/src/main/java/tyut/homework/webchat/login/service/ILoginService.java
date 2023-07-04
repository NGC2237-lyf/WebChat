package tyut.homework.webchat.login.service;

import tyut.homework.webchat.common.domain.User;

public interface ILoginService {

    //登陆校验
    public User judgeMsg(String id,String password);

    //查看数据库是否已存在邮箱
    public User isExistEmail(String email);

    //根据邮箱插入数据
    public int insertDBByEmail(User user);

}
