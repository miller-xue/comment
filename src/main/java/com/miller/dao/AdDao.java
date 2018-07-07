package com.miller.dao;

import com.miller.bean.Ad;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 */
public interface AdDao {

    int insert(Ad ad);

    List<Ad> selectByPage(Ad ad);

}
