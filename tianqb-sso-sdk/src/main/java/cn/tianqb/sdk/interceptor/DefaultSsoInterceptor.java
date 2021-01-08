package cn.tianqb.sdk.interceptor;

import cn.tianqb.sdk.common.LoginContext;
import cn.tianqb.sdk.common.SsoConstant;
import cn.tianqb.sdk.exception.SsoException;
import cn.tianqb.sdk.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/6 10:40
 */
@Component
public class DefaultSsoInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private SsoService ssoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(SsoConstant.TOKEN_NAME);
        LoginContext loginContext = ssoService.getInfo(String.join(SsoConstant.SEPARATOR, SsoConstant.TOKEN_PREFIX, token));
        if (!ObjectUtils.isEmpty(loginContext)) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
