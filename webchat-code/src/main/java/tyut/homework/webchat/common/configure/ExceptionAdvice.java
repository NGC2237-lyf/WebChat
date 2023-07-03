package tyut.homework.webchat.common.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tyut.homework.webchat.common.utils.Result;
import tyut.homework.webchat.login.Exception.LoginMsgException;
import tyut.homework.webchat.login.Exception.VerifyCodeException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice{
    @ExceptionHandler(LoginMsgException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleNormalApplicationException(LoginMsgException exception) {
        log.warn("A unknown runtime exception:",exception.getMessage());
        return Result.error(exception.getMessage());
    }

    @ExceptionHandler(VerifyCodeException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleNormalApplicationException(VerifyCodeException exception) {
        log.warn("A unknown runtime exception:",exception.getMessage());
        return Result.error(exception.getMessage());
    }
}
