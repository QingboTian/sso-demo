package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/8 15:24
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    /**
     *
     */
    DELETED(-1, "删除"),
    NORMAL(1, "未删除");

    private Integer code;
    private String desc;
}
