package com.miller.service.impl;

import com.miller.bean.Member;
import com.miller.cache.CodeCache;
import com.miller.cache.TokenCache;
import com.miller.dao.MemberDao;
import com.miller.service.MemberService;
import com.miller.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miller on 2018/7/9
 */


@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    public boolean exists(Long phone) {
        // 封装查询条件
        Member member = new Member();
        member.setPhone(phone);

        List<Member> members = memberDao.select(member);
        return members != null && members.size() == 1;
    }

    public boolean saveCode(Long phone, String code) {
        // TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.save(phone, MD5Util.getMD5(code));
    }


    public boolean sendCode(Long phone, String content) {
        log.info(phone + "|" + content);
        return true;
    }

    public String getCode(Long phone) {
        // TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    public void saveToken(String token, Long phone) {
        TokenCache tokenCache = TokenCache.getInstance();
        tokenCache.save(token, phone);
    }

    public Long getPhone(String token) {
        TokenCache tokenCache = TokenCache.getInstance();
        return tokenCache.getPhone(token);
    }

    public Long getIdByPhone(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.select(member);
        if (list != null && list.size() == 1) {
            return list.get(0).getId();
        }
        return null;
    }
}
