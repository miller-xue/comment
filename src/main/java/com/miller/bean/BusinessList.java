package com.miller.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Created by miller on 2018/7/5
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessList {

    private boolean hasMore;

    private List<Business> data;

}
