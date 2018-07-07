package com.miller.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miller.bean.BusinessList;
import com.miller.config.AdConfig;
import com.miller.dto.AdDto;
import com.miller.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @RequestMapping(value = "/homelist/{city}/{page}", method = RequestMethod.GET)
    public BusinessList homelist() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String s = "{\"hasMore\":true,\"data\":[{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201638030-473660627.png\",\"title\":\"汉堡大大\",\"subTitle\":\"叫我汉堡大大，还你多彩口味\",\"price\":\"28\",\"distance\":\"120m\",\"mumber\":\"389\",\"id\":\"8708146484409796\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"北京开源饭店\",\"subTitle\":\"[望京]自助晚餐\",\"price\":\"98\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"6214259783210612\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201652952-1050532278.png\",\"title\":\"服装定制\",\"subTitle\":\"原价xx元，现价xx元，可修改一次\",\"price\":\"1980\",\"distance\":\"160\",\"mumber\":\"106\",\"id\":\"022246982410528915\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201700186-1351787273.png\",\"title\":\"婚纱摄影\",\"subTitle\":\"免费试穿，拍照留念\",\"price\":\"2899\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"9906988578254157\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\"title\":\"麻辣串串烧\",\"subTitle\":\"双人免费套餐等你抢购\",\"price\":\"0\",\"distance\":\"160\",\"mumber\":\"1426\",\"id\":\"23579379621990926\"}]}";
        return mapper.readValue(s, new TypeReference<BusinessList>() {
        });
    }

    /**
     * // 搜索结果页 - 搜索结果 - 四个参数
     */
    @RequestMapping(value = "/search/{page}/{city}/{category}/{keyword}", method = RequestMethod.GET)
    public void search(@PathVariable(value = "page") String page,
                       @PathVariable(value = "city") String city,
                       @PathVariable(value = "category") String category,
                       @PathVariable(value = "keyword") String keyword) {

    }

    /**
     * // 搜索结果页 - 搜索结果 - 三个参数
     */
    @RequestMapping(value = "/search/{page}/{city}/{category}", method = RequestMethod.GET)
    public void search(@PathVariable(value = "page") String page,
                       @PathVariable(value = "city") String city,
                       @PathVariable(value = "category") String category) {

    }

    /**
     * 详情页 - 商户信息
     *
     * @param id
     */
    @RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
    public void detailInfo(@PathVariable(value = "id") String id) {

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
