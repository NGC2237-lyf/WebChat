package tyut.homework.webchat.login.Exception;

import tyut.homework.webchat.common.configure.BaseException;

public class VerifyCodeException extends BaseException {
    public VerifyCodeException() {

    }

    public VerifyCodeException(String message) {
        super(message);
    }

    public VerifyCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyCodeException(Throwable cause) {
        super(cause);
    }

    protected VerifyCodeException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
