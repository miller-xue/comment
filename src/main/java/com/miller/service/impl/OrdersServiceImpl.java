package com.miller.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.miller.bean.Orders;
import com.miller.config.BusinessConfig;
import com.miller.constant.CommentStateConst;
import com.miller.dao.OrdersDao;
import com.miller.dto.OrdersDto;
import com.miller.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miller on 2018/7/11
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao dao;

    @Autowired
    private BusinessConfig businessConfig;

    /**
     * 买单
     * @param ordersDto
     * @return
     */
    @Transactional
    public boolean add(OrdersDto ordersDto) {
        try {
            Orders orders = new Orders();
            BeanUtils.copyProperties(ordersDto, orders);
            orders.setCommentState(CommentStateConst.NOT_COMMENT);
            dao.insert(orders);
            // 修改已售数量
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

    public List<OrdersDto> getListByMemberId(Long memberId) {
        Orders orders = new Orders();
        orders.setMemberId(memberId);
        List<Orders> list = dao.select(orders);

        List<OrdersDto> result = new ArrayList<OrdersDto>();
        for (Orders o : list) {
            OrdersDto ordersDto = new OrdersDto();
            result.add(ordersDto);
            BeanUtils.copyProperties(o, ordersDto);
            ordersDto.setImg(businessConfig.getUrl() + o.getBusiness().getImgFileName());
            ordersDto.setTitle(o.getBusiness().getTitle());
            ordersDto.setCount(o.getBusiness().getNumber());
        }
        return result;
    }
}
