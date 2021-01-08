package cn.tianqb.sdk.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/6 14:17
 */
@ControllerAdvice
@Slf4j
public class SsoExceptionController {

    @ExceptionHandler(value = SsoException.class)
    @ResponseBody
    public Map<String, Object> ssoException(SsoException ex) {
        log.info("SsoExceptionController#ssoException", ex);
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", ex.getCode());
        result.put("msg", ex.getMessage());
        return result;
    }
}
