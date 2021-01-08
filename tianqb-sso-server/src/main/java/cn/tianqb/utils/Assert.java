package cn.tianqb.utils;

import java.util.Collection;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 16:06
 */
public class Assert extends org.springframework.util.Assert {

    public static void isEmpty(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
        if (object instanceof String) {
            String str = (String) object;
            if (str.trim().length() == 0) {
                throw new IllegalArgumentException(message);
            }
        }
        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (collection.size() == 0) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
