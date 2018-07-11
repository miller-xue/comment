package com.miller.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by miller on 2018/7/11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orders {

    private Long id;
    private Long memberId;
    private Long businessId;
    private Integer num;
    private Integer commentState;
    private Double price;

    private Business business;
    private Member member;
}
