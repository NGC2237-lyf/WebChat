package tyut.homework.webchat.login.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.login.service.ILoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    /**
     * 登陆校验
     *
     * @param session
     * @return
     */
    @PostMapping("/check")
    public Result Check(String verifyCode,String id,String password, HttpSession session){
        //获取验证码
        String oldVerifyCode = (String) session.getAttribute("verifyCode");
        //非空判断
        if (verifyCode == null){
            return Result.error("验证码不能为空");
        }
        if (!verifyCode.equals(oldVerifyCode)){
            return Result.error("验证码不正确");
        }
        return Result.success(iLoginService.judgeMsg(id, password));
    }


    /**
     * 生成验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping("/verify")
    public Result Verify(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,HttpSession session) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 4, 4);
        String base64Image = captcha.getImageBase64();
        //获取验证码中的文字内容
        session.setAttribute("verifyCode",captcha.getCode());
        return Result.success((Object) base64Image);
    }
}
