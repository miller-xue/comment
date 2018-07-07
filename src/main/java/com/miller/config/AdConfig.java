package com.miller.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by miller on 2018/7/7
 */

@Component
@Getter
public class AdConfig {
    @Value("${ad.number}")
    private int adNumber;
}
