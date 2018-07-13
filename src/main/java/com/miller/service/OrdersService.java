package com.miller.service;

import com.miller.dto.OrdersDto;

import java.util.List;

/**
 * Created by miller on 2018/7/11
 */
public interface OrdersService {

    boolean add(OrdersDto ordersDto);

    OrdersDto getById(Long id);

    /**
     * 根据会员ID获取该会员的全部订单dto列表
     * @param memberId 会员ID
     * @return 会员的订单dto列表
     */
    List<OrdersDto> getListByMemberId(Long memberId);
}
