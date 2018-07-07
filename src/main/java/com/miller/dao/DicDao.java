package com.miller.dao;

import com.miller.bean.Dic;

import java.util.List;

/**
 * Created by miller on 2018/7/7
 */
public interface DicDao {

    List<Dic> select(Dic dic);
}
