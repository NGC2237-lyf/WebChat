package tyut.homework.webchat.login.Exception;

import tyut.homework.webchat.common.configure.BaseException;

public class LoginMsgException extends BaseException {
    public LoginMsgException() {

    }

    public LoginMsgException(String message) {
        super(message);
    }

    public LoginMsgException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginMsgException(Throwable cause) {
        super(cause);
    }

    protected LoginMsgException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
