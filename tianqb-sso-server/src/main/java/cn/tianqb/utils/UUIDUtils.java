package cn.tianqb.utils;

import java.util.UUID;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 15:34
 */
public class UUIDUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
