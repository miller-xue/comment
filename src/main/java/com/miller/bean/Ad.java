package com.miller.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by miller on 2018/7/5
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ad extends BaseBean {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文件地址
     */
    private String imgFileName;
    /**
     * 图片点击连接
     */
    private String link;
    /**
     * 权重
     */
    private Long weight;
}
