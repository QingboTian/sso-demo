package cn.tianqb.sso.domain;

import lombok.Data;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 16:32
 */
@Data
public class AccessToken {

    private String token;
    private long expire;

}
