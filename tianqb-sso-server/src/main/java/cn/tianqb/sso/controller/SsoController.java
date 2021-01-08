package cn.tianqb.sso.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.sso.domain.AccessToken;
import cn.tianqb.sso.domain.vo.LoginVo;
import cn.tianqb.sso.service.SsoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 16:24
 */
@Slf4j
@RestController
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private SsoServiceImpl ssoServiceImpl;

    @PostMapping
    public WebResult login(LoginVo loginVo) {
        AccessToken accessToken = ssoServiceImpl.login(loginVo.getUsername(), loginVo.getPassword());
        return accessToken == null ? WebResult.error(HttpStatus.UNAUTHORIZED.value(), "登录失败") :
                WebResult.success(accessToken);
    }

}
