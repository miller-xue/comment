package com.miller.service;

import com.miller.dto.OrdersDto;

/**
 * Created by miller on 2018/7/11
 */
public interface OrdersService {

    boolean add(OrdersDto ordersDto);

    OrdersDto getById(Long id);
}
