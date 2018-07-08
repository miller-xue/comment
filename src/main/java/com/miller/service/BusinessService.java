package com.miller.service;

import com.miller.dto.BusinessDto;
import com.miller.dto.BusinessListDto;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 */
public interface BusinessService {

    /**
     * 根据id获得对象
     * @param id
     * @return
     */
    BusinessDto getById(Long id);

    /**
     * 新增
     * @param businessDto 商户dto对象
     * @return 是否新增成功：true-成功;fale-失败
     */
    boolean add(BusinessDto businessDto);

    /**
     * 修改
     * @param businessDto
     * @return
     */
    boolean update(BusinessDto businessDto);

    /**
     * 分页搜索商户列表
     *
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表
     */
    List<BusinessDto> searchByPage(BusinessDto businessDto);


    /**
     * 分页搜索商户列表(接口专用)
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表Dto对象
     */
    BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
