package com.miller.dao;

import com.miller.bean.Orders;

import java.util.List;

/**
 * Created by miller on 2018/7/11
 */
public interface OrdersDao {

    int insert(Orders orders);


    /**
     * 根据主键查询订单表对象
     * @param id 主键值
     * @return 订单表对象
     */
    Orders selectByid(Long id);


    /**
     * 修改
     * @param orders 订单表对象
     * @return 影响行数
     */
//    int update(Orders orders);

    /**
     * 根据条件查询订单列表
     * @param orders 查询条件
     * @return 订单列表
     */
    List<Orders> select(Orders orders);
}
