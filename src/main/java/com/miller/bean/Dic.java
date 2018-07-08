package com.miller.bean;

import lombok.Data;

/**
 * 字典bean
 * Created by miller on 2018/7/7
 */
@Data
public class Dic {
    /**
     * 类型
     */
    private String type;
    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 权重
     */
    private Integer weight;

}
