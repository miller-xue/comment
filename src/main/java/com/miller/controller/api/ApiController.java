package com.miller.controller.api;

import com.miller.config.AdConfig;
import com.miller.config.BusinessConfig;
import com.miller.constant.ApiCodeEnum;
import com.miller.dto.*;
import com.miller.service.AdService;
import com.miller.service.BusinessService;
import com.miller.service.MemberService;
import com.miller.service.OrdersService;
import com.miller.util.CommonUtil;
import com.miller.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手机端APi
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

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrdersService ordersService;

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
    public ApiCodeDto order(OrderForBuyDto orderForBuyDto) {
        ApiCodeDto result = null;

        Long phone = memberService.getPhone(orderForBuyDto.getToken());
        // 查询商家
        BusinessDto businessDto = businessService.getById(orderForBuyDto.getId());
        if (businessDto == null) {
            //TODO
        }

        if (phone != null && phone.equals(orderForBuyDto.getUsername())) {
            // 根据手机号获得会员id
            Long memberId = memberService.getIdByPhone(phone);
            OrdersDto ordersDto = new OrdersDto();
            ordersDto.setNum(orderForBuyDto.getNum());
            ordersDto.setPrice(orderForBuyDto.getPrice());
            ordersDto.setBusinessId(orderForBuyDto.getId());
            ordersDto.setMemberId(memberId);
            ordersService.add(ordersDto);
            result = new ApiCodeDto(ApiCodeEnum.SUCCESS);
        }else {
            result = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
        }
        return result;
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiCodeDto login(@RequestParam("username") Long phone, @RequestParam("code") String code) {
        ApiCodeDto result = null;
        // 1、用手机号取出保存的md5(6位随机数)，能取到，并且与提交上来的code值相同为校验通过
        String saveCode = memberService.getCode(phone);
        if (saveCode != null) {
            // 2、如果校验通过，生成一个32位的token
            if (code.equals(saveCode)) {
                String token = CommonUtil.getUUID();
                memberService.saveToken(token, phone);
                // 4.把Token返回给前端
                // 4、将这个token返回给前端
                result = new ApiCodeDto(ApiCodeEnum.SUCCESS);
                result.setToken(token);
            }else {
                result = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
            }
        }else {
            result = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
        }

        return result;
    }

    /**
     * 获取短信验证码
     *
     * @return
     */
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ApiCodeDto sms(@RequestParam("username") Long phone) {
        ApiCodeDto apiCodeDto = null;
        // 1.验证手机是否存在(是否注册)
        if (memberService.exists(phone)) {
            // 2.生成6位随机数
            String code = String.valueOf(CommonUtil.random(6));
            // 3.保存手机号与对应的md5(6位随机数)(一般保存1分钟)
            if (memberService.saveCode(phone, code)) {
                // 4、调用短信通道，将明文6位随机数发送到对应的手机上。
                if (memberService.sendCode(phone, code)) {
                    apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(), code);
                }else {
                    apiCodeDto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
                }
            }else {
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
            }
        }else {
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }

        return apiCodeDto;
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
