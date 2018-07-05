package com.miller.bean;

import lombok.Data;

/**
 * Created by miller on 2018/7/5
 */
@Data
public class Business {
    private Long id;
    private String imgFileName;
    private String title;
    private String subtitle;
    private Double price;
    private Integer distance;
    private Integer number;
    private String desc;
    private String city;
    private String category;
    private Long starTotalNum;
    private Long commentTotalNum;
}
