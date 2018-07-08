package com.miller.service.impl;

import com.miller.bean.Business;
import com.miller.bean.Page;
import com.miller.config.BusinessConfig;
import com.miller.constant.CategoryConst;
import com.miller.dao.BusinessDao;
import com.miller.dto.BusinessDto;
import com.miller.dto.BusinessListDto;
import com.miller.service.BusinessService;
import com.miller.util.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miller on 2018/7/7
 * 商家Service
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private BusinessConfig businessConfig;




    public BusinessDto getById(Long id) {
        Business business = businessDao.selectById(id);
        BusinessDto dto = new BusinessDto();
        BeanUtils.copyProperties(business, dto);
        dto.setImg(businessConfig.getUrl() + business.getImgFileName());
        dto.setStar(this.getStar(business));
        return dto;
    }

    public boolean add(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);

        // 获取文件对象
        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
            try {
                String imgFileName = FileUtils.save(businessDto.getImgFile(), businessConfig.getSavePath());
                business.setImgFileName(imgFileName);
                // 默认已售数量为0
                business.setNumber(0);
                // 默认评论总次数为0
                business.setCommentTotalNum(0L);

                // 默认评论星星总数为0
                business.setStarTotalNum(0L);
                businessDao.insert(business);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    @Transactional
    public boolean update(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);
        try {
            if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
                // 删掉旧图片
                String imgFileName = FileUtils.save(businessDto.getImgFile(), businessConfig.getSavePath());
                FileUtils.removeFile(businessConfig.getSavePath(),businessDto.getImgFileName());
                business.setImgFileName(imgFileName);
            }
            businessDao.update(business);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BusinessDto> searchByPage(BusinessDto businessDto) {
        // 转换搜索条件
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);
        // 搜索
        List<Business> businessList = businessDao.selectByPage(business);
        // 转换结果集
        List<BusinessDto> result = new ArrayList<BusinessDto>();
        for (Business b : businessList) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(b, businessDtoTemp);
            businessDtoTemp.setImg(businessConfig.getUrl() + b.getImgFileName());
            businessDtoTemp.setStar(this.getStar(business));
        }
        return result;
    }

    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {
        BusinessListDto result = new BusinessListDto();

        Business condition = new Business();
        BeanUtils.copyProperties(businessDto, condition);
        //当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中
        if (!StringUtils.isEmpty(businessDto.getKeyword())) {
            condition.setTitle(businessDto.getKeyword());
            condition.setSubtitle(businessDto.getKeyword());
            condition.setDesc(businessDto.getKeyword());
        }

        // 如果类别全部(all) 需要把类别清空 不做为过滤条件
        if (condition.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            condition.setCategory(null);
        }
        // 前端 app页码从0开始,这里需要+1
        condition.getPage().setCurrentPage(condition.getPage().getCurrentPage() + 1);
        List<Business> businessList = businessDao.selectLikeByPage(condition);

        Page page = condition.getPage();
        // 查询后page 设置对象 hasMore
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());
        for (Business business : businessList) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
            businessDtoTemp.setImg(businessConfig.getUrl() + business.getImgFileName());
            businessDtoTemp.setMumber(business.getNumber());
        }

        return result;
    }


    private int getStar(Business business) {
        if(business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (int)(business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0;
        }
    }

}
