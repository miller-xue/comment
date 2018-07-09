package com.miller.dao;

import com.miller.bean.Member;

import java.util.List;

/**
 * Created by miller on 2018/7/9
 */
public interface MemberDao {
    /**
     * 根据查询条件查询会员列表
     * @param member 查询条件
     * @return 会员列表
     */
    List<Member> select(Member member);


    int insert(Member member);

    int delete(Long id);
}
