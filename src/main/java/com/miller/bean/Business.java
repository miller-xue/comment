package com.miller.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by miller on 2018/7/5
 * 商家bean对象
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Business extends BaseBean {
    private Long id;
    /* 图片文件名 */
    private String imgFileName;

    /* 标题 */
    private String title;

    /* 副标题 */
    private String subtitle;

    /* 价格(单位：元) */
    private Double price;

    /* 距离(单位：米) */
    private Integer distance;

    /* 已售数量 */
    private Integer number;

    /* 描述 */
    private String desc;

    /* 城市 */
    private String city;

    /* 类别 */
    private String category;

    private Long starTotalNum;

    private Long commentTotalNum;



    private Dic cityDic;

    private Dic categoryDic;

}
