package cn.tianqb.sso.domain.vo;

import lombok.Data;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 16:30
 */
@Data
public class LoginVo {

    private String username;
    private String password;
    private String checkCode;

}
