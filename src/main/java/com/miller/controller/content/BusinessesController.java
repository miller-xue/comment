package com.miller.controller.content;

import com.miller.constant.DicTypeConst;
import com.miller.constant.PageCodeEnum;
import com.miller.dto.BusinessDto;
import com.miller.service.BusinessService;
import com.miller.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by miller on 2018/7/7
 */
@Controller
@RequestMapping(value = "/businesses")
public class BusinessesController {

    @Autowired
    private BusinessService service;

    @Autowired
    private DicService dicService;

    /**
     * 商户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model, BusinessDto businessDto) {
        model.addAttribute("searchParam", businessDto);
        model.addAttribute("list", service.searchByPage(businessDto));
        return "content/businessList";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String remove(@PathVariable("id") Long id) {
        System.out.println(id);
        //TODO
        return "content/businessList";
    }

    /**
     * 新增页面
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addInit(Model model) {
        model.addAttribute("cityList",dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList",dicService.getListByType(DicTypeConst.CATEGORY));
        return "content/businessAdd";
    }

    /**
     * 保存
     *
     * @param dto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(BusinessDto dto, Model model) {
        if (service.add(dto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "redirect:/businesses";
    }

    /**
     * 修改页面初始化
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") Long id,
                        Model model) {
        model.addAttribute("cityList",dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList",dicService.getListByType(DicTypeConst.CATEGORY));
        model.addAttribute("modifyObj", service.getById(id));
        return "content/businessModify";
    }

    /**
     * 商户修改
     * @param id
     * @param businessDto
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String modify(@PathVariable("id") Long id, BusinessDto businessDto,Model model) {
        if (service.update(businessDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "redirect:/businesses";
    }
}
