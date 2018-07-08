package com.miller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by miller on 2018/7/7
 * 系统枚举
 */

@Getter
@AllArgsConstructor
public enum PageCodeEnum {

    ADD_SUCCESS(1000,"新增成功"),
    ADD_FAIL(1001,"新增失败"),

    MODIFY_SUCCESS(1100,"修改成功"),
    MODIFY_FAIL(1101,"修改失败"),
    ;
    public static final String KEY = "pageCode";

    private Integer code;

    private String msg;
}
