package com.miller.service.impl;

import com.miller.bean.Business;
import com.miller.config.ImageConfig;
import com.miller.dao.BusinessDao;
import com.miller.dto.BusinessDto;
import com.miller.service.BusinessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by miller on 2018/7/7
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private ImageConfig imageConfig;


    public BusinessDto getById(Long id) {
        Business business = businessDao.selectById(id);
        BusinessDto dto = new BusinessDto();
        BeanUtils.copyProperties(business, dto);
        dto.setImg(imageConfig.getUrl() + business.getImgFileName());
        dto.setStar(this.getStar(business));
        return dto;
    }

    public boolean add(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);

        return false;
    }


    private int getStar(Business business) {
        if(business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (int)(business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0;
        }
    }

}
