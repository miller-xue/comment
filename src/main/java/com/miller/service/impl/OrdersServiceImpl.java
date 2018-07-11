package com.miller.service.impl;

import com.miller.bean.Orders;
import com.miller.dao.OrdersDao;
import com.miller.dto.OrdersDto;
import com.miller.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by miller on 2018/7/11
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao dao;

    public boolean add(OrdersDto ordersDto) {
        try {
            Orders orders = new Orders();
            BeanUtils.copyProperties(ordersDto, orders);
            dao.insert(orders);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public OrdersDto getById(Long id) {
        OrdersDto ordersDto = new OrdersDto();
        Orders orders = dao.selectByid(id);
        BeanUtils.copyProperties(orders, ordersDto);
        return ordersDto;
    }
}
