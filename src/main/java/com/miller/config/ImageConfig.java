package com.miller.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by miller on 2018/7/7
 */
@Component
@Getter
public class ImageConfig {

    @Value("${image.savePath}")
    private String savePath;

    @Value("${image.url}")
    private String url;
}
