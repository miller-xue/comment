package com.miller.service.impl;

import com.miller.bean.Dic;
import com.miller.dao.DicDao;
import com.miller.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 */
@Service
public class DicServiceImpl implements DicService {

    @Autowired
    private DicDao dicDao;

    public List<Dic> getListByType(String type) {
        Dic dic = new Dic();
        dic.setType(type);
        return dicDao.select(dic);
    }
}
