package com.miller.bean;

import lombok.Data;

/**
 * Created by miller on 2018/7/9
 */
@Data
public class Member {
    private Long id;

    /**
     * 电话
     */
    private Long phone;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}
