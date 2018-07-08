package com.miller.controller.content;

import com.miller.constant.PageCodeEnum;
import com.miller.dto.AdDto;
import com.miller.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by miller on 2018/7/7
 * 广告控制器
 */
@Controller
@RequestMapping(value = "/ad")
public class AdController {



    @Autowired
    private AdService adService;


    @RequestMapping
    public String init() {
        return "redirect:/ad/search";
    }

    /**
     * 根据条件搜索
     * @param model
     * @param adDto
     * @return
     */
    @RequestMapping(value = "/search")
    public String search(Model model, AdDto adDto) {
        model.addAttribute("list",adService.searchByPage(adDto));
        model.addAttribute("searchParam", adDto);
        return "content/adList";
    }


    @RequestMapping("/addInit")
    public String addInit() {
        return "content/adAdd";
    }

    /**
     * 添加
     * @param adDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(AdDto adDto, Model model) {
        if(adService.add(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
        }
        return "content/adAdd";
    }
}
