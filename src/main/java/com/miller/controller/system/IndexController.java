package com.miller.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by miller on 2018/7/7
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String init() {
        return "system/index";
    }
}
