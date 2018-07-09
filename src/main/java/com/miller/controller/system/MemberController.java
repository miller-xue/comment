package com.miller.controller.system;

import com.miller.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by miller on 2018/7/9
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService service;
}
