package com.miller.dao;

import com.miller.bean.Orders;

/**
 * Created by miller on 2018/7/11
 */
public interface OrdersDao {

    int insert(Orders orders);

    Orders selectByid(Long id);
}
