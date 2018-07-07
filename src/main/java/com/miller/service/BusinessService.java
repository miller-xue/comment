package com.miller.service;

import com.miller.bean.Business;
import com.miller.dto.BusinessDto;

/**
 * Created by miller on 2018/7/7
 */
public interface BusinessService {

    BusinessDto getById(Long id);

    /**
     * 新增
     * @param BusinessDto 商户dto对象
     * @return 是否新增成功：true-成功;fale-失败
     */
    boolean add(BusinessDto businessDto);
}
