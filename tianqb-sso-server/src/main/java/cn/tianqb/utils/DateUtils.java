package cn.tianqb.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 16:38
 */
public class DateUtils {

    public static long getTime(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
