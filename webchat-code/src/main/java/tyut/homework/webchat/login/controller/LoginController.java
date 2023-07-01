package tyut.homework.webchat.login.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.login.dto.LoginDTO;
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
     * @param loginDTO
     * @param session
     * @return
     */
    @GetMapping("/check")
    public Result Check(@Validated LoginDTO loginDTO, HttpSession session){
        //获取验证码
        String oldVerifyCode= (String) session.getAttribute("verifyCode");
        //判断验证码是否正确
        String verifyCode = loginDTO.getVerifyCode();
        //非空判断
        if (verifyCode == null){
            return Result.error("验证码不能为空");
        }
        if (verifyCode != oldVerifyCode){
            return Result.error("验证码不正确");
        }
        //判断用户信息是否正确
        String email = loginDTO.getEamil();
        String password = loginDTO.getPassword();
        return Result.success(iLoginService.judgeMsg(email, password));
    }


    /**
     * 生成验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping("/verify")
    public void Verify(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write(httpServletResponse.getOutputStream());
        //获取验证码中的文字内容
        String verifyCode = captcha.getCode();
        httpServletRequest.getSession().setAttribute("verifyCode",verifyCode);
    }


}
