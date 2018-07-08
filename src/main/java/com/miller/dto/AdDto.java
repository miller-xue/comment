package com.miller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miller.bean.Ad;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by miller on 2018/7/7
 * 广告DTO
 */

@Data
public class AdDto extends Ad {
    private String img;

    private MultipartFile imgFile;
}
