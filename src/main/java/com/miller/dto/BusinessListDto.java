package com.miller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miller.bean.Business;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miller on 2018/7/5
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessListDto {
    /**
     * 是否有更多
     */
    private boolean hasMore;

    /**
     * 商家DTO LIST
     */
    private List<BusinessDto> data;

    public BusinessListDto() {
        data = new ArrayList<BusinessDto>();
    }

}
