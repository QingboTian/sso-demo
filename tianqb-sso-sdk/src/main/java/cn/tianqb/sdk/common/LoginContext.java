package cn.tianqb.sdk.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/6 10:42
 */
@Data
public class LoginContext implements Serializable {
    private static final long serialVersionUID = -7046998006465140955L;

    /**
     * 唯一标识
     */
    private String openId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 性别
     */
    private Integer gender;
}
