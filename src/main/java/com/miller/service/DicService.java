package com.miller.service;

import com.miller.bean.Dic;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 */
public interface DicService {

    public List<Dic> getListByType(String type);

}
