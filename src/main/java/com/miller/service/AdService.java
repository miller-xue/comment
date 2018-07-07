package com.miller.service;

import com.miller.bean.Ad;
import com.miller.dto.AdDto;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 */
public interface AdService {

    /**
     * 新增广告
     * @param adDto
     * @return 是否新增成功：true-成功;false-失败
     */
    boolean add(AdDto adDto);

    /**
     * 分页搜索广告列表
     * @param adDto 查询条件(包括分页对象)
     * @return 广告列表
     */
    List<AdDto> searchByPage(AdDto adDto);
}
