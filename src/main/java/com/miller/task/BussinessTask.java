package com.miller.task;

import com.miller.dao.BusinessDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by miller on 2018/7/13
 * 商户相关的定时任务
 */
@Slf4j
@Component("BussinessTask")
public class BussinessTask {

    @Autowired
    private BusinessDao dao;

    /**
     *同步已售数量
     */
    @Transactional
    public void syncNumber() {
        dao.updateNumber();
    }
}
