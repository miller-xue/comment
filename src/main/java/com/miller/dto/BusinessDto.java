package com.miller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miller.bean.Business;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by miller on 2018/7/7
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDto extends Business {
    private String img;
    private MultipartFile imgFile;
    private String keyword;
    private Integer mumber;
    private Integer star;
}
