package tyut.homework.webchat.login.controller;

import lombok.NonNull;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.common.utils.ValidateCodeUtils;
import tyut.homework.webchat.login.service.ILoginService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private ILoginService iLoginService;

    String emailCode = null;

    /**
     * 注册
     *
     * @return
     */
    @PostMapping
    public Result userRegister(@NonNull String nickName,@NonNull String password,@NonNull String code,@NonNull String email) {
        //判断邮箱是否在数据库存在
        User existEmail = iLoginService.isExistEmail(email);
        if (existEmail == null) {
            String emailCode = code;
            //code校验
            String oldCode = this.emailCode;
            if (emailCode == null || oldCode == null) {
                return Result.error("验证码填写错误");
            }
            if (!emailCode.equals(oldCode)) {
                return Result.error("验证码填写错误");
            }
            //注册成功，插入数据
            int i = iLoginService.insertDBByEmail(new User(nickName, password,email));
            if (i == 1) {
                return Result.success("注册成功");
            }
        }else{
            return Result.error("注册失败，该账户已存在");
        }
        return Result.error("其他未知错误，请联系写java的那个");
    }

    /**
     * 发送邮箱验证码
     *
     * @return
     */
    @GetMapping("/sendEmail")
    public Result sendEmailCode(@NonNull String email) {
        try {
            HtmlEmail self_email = new HtmlEmail();
            //设置邮件服务器的名称
            self_email.setHostName("smtp.qq.com");
            self_email.setCharset("UTF-8");
            // 收件人的邮箱地址
            self_email.addTo(email);
            //此处填发件人邮箱地址和用户名,用户名可以任意填写
            self_email.setFrom("1143658620@qq.com", "aa");
            //发件人的邮箱地址 和 发件人的授权码；这个授权码是需要发件人提前开通和生成的
            self_email.setAuthentication("1143658620@qq.com", "ubfgkstrqnmcieee");
            int emailCode = ValidateCodeUtils.generateValidateCode(6);
            self_email.setSubject("WebChat");//此处填写邮件主题
            self_email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + emailCode);//设置邮件正文
            this.emailCode = String.valueOf(emailCode);
            self_email.send();
            return Result.success("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

