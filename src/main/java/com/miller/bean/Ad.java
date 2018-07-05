package com.miller.bean;

import lombok.Data;

/**
 * Created by miller on 2018/7/5
 */
@Data
public class Ad {
    private Long id;
    private String title;
    private String imgFileName;
    private String link;
    private Long weight;
}
