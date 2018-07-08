package com.miller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miller.bean.Business;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by miller on 2018/7/7
 * 商家DTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDto extends Business {
    /**
     * 图片地址
     */
    private String img;

    /**
     * 上传文件工具类
     */
    private MultipartFile imgFile;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 数量
     */
    private Integer mumber;

    /**
     * 点赞数
     */
    private Integer star;
}
