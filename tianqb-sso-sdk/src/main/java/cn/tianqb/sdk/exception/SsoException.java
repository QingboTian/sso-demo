package cn.tianqb.sdk.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 登录异常
 *
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/6 14:12
 */
@Getter
@Setter
public class SsoException extends RuntimeException {

    private int code;

    public SsoException() {
    }

    public SsoException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SsoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SsoException(Throwable cause) {
        super(cause);
    }

    public SsoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
