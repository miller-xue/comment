package com.miller.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miller.config.AdConfig;
import com.miller.config.BusinessConfig;
import com.miller.dto.AdDto;
import com.miller.dto.BusinessDto;
import com.miller.dto.BusinessListDto;
import com.miller.service.AdService;
import com.miller.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by miller on 2018/7/5
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private AdConfig adConfig;

    @Autowired
    private AdService adService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private BusinessConfig businessConfig;

    /**
     * 首页 —— 广告（超值特惠）
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/homead", method = RequestMethod.GET)
    public List<AdDto> homead() throws IOException {
        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adConfig.getAdNumber());
        return adService.searchByPage(adDto);
    }

    /**
     * 首页 —— 推荐列表（猜你喜欢）
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
    public BusinessListDto homelist(BusinessDto businessDto) throws IOException {
        businessDto.getPage().setPageNumber(businessConfig.getHomeNumber());
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * // 搜索结果页 - 搜索结果 - 四个参数
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}", method = RequestMethod.GET)
    public BusinessListDto searchByKeyword(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessConfig.getSearchNumber());
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * // 搜索结果页 - 搜索结果 - 三个参数
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}", method = RequestMethod.GET)
    public BusinessListDto search(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessConfig.getSearchNumber());
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 详情页 - 商户信息
     *
     * @param id
     */
    @RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
    public BusinessDto detailInfo(@PathVariable(value = "id") Long id) {
        return businessService.getById(id);
    }

    /**
     * 详情页 - 用户评论
     *
     * @param id
     */
    @RequestMapping(value = "/detail/comment/{page}/{id}", method = RequestMethod.GET)
    public void detailComment(@PathVariable(value = "page") String page,
                              @PathVariable(value = "id") String id) {

    }

    /**
     * 订单列表
     *
     * @return
     */
    @RequestMapping(value = "/orderlist/:username", method = RequestMethod.GET)
    public void orderList(@PathVariable(value = "username") String username) {

    }


    /**
     * 购买
     *
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Map<String, Object> buy() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errno", 0);
        result.put("msg", "buy ok");
        return result;
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errno", 1);
        result.put("msg", "loing not ok");
        return result;
    }

    /**
     * 获取短信验证码
     *
     * @return
     */
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public Map<String, Object> sms() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errno", 0);
        result.put("msg", "lok");
        result.put("code", "123456");
        return result;
    }


    /**
     * 提交评论
     *
     * @return
     */
    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    public Map<String, Object> submitComment() {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errno", 0);
        result.put("msg", "ok");
        return result;
    }

}
