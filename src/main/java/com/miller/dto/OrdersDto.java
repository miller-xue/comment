package com.miller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miller.bean.Orders;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by miller on 2018/7/11
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersDto extends Orders {
    private String img;
    private String title;
    private Integer count;
}
