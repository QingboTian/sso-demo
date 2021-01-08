package cn.tianqb.sdk.service;

import cn.tianqb.sdk.common.LoginContext;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/6 14:06
 */
public interface SsoService {
    boolean isLogin(String token);

    LoginContext getInfo(String token);
}
