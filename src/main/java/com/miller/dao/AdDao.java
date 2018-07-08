package com.miller.dao;

import com.miller.bean.Ad;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 * 广告dao
 */
public interface AdDao {

    /**
     * 插入
     * @param ad
     * @return
     */
    int insert(Ad ad);

    /**
     * 分页搜索广告
     * @param ad
     * @return
     */
    List<Ad> selectByPage(Ad ad);

}
