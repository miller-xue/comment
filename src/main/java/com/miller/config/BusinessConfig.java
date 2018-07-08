package com.miller.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by miller on 2018/7/8
 * 商家常量
 */
@Component
@Getter
public class BusinessConfig {

    @Value("${business.image.savePath}")
    private String savePath;

    @Value("${business.image.url}")
    private String url;


    @Value("${business.home.number}")
    private int homeNumber;

    @Value("${business.search.number}")
    private int searchNumber;
}
