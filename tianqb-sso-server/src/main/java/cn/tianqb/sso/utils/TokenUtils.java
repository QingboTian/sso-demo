package cn.tianqb.sso.utils;

import cn.tianqb.utils.UUIDUtils;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 15:33
 */
public class TokenUtils {

    public static String accessToken(){
        return UUIDUtils.uuid();
    }
}
