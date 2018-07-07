package com.miller.dao;

import com.miller.bean.Business;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 */
public interface BusinessDao {

    /**
     * 新增
     * @param business 商户表对象
     * @return 影响行数
     */
    int insert(Business business);

    /**
     *  根据主键查询商户
     * @param id 主键
     * @return 商户对象
     */
    Business selectById(Long id);

    /**
     *  根据查询条件分页查询商户列表
     * @param business 查询条件
     * @return 商户列表
     */
    List<Business> selectByPage(Business business);

}
