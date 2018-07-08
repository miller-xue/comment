package com.miller.service.impl;

import com.miller.bean.Ad;
import com.miller.config.AdConfig;
import com.miller.dao.AdDao;
import com.miller.dto.AdDto;
import com.miller.service.AdService;
import com.miller.util.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miller on 2018/7/7
 * 广告Service
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdConfig adConfig;

    @Autowired
    private AdDao adDao;

    //TODO 可以失败信息更详细
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        ad.setId(adDto.getId());
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {

//            File fileFolder = new File(adConfig.getSavePath());
//            if (!fileFolder.exists()) {
//                fileFolder.mkdirs();
//            }
//            String imgFileName = System.currentTimeMillis() + "_" + adDto.getImgFile().getOriginalFilename();
//            File file = new File(fileFolder, imgFileName);
            //adDto.getImgFile().transferTo(file);
            try {

                String imgFileName = FileUtils.save(adDto.getImgFile(), adConfig.getSavePath());
                ad.setImgFileName(imgFileName);
                adDao.insert(ad);
                return true;
            } catch (IOException e) {
                // TODO
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result = new ArrayList<AdDto>();
        // 转化搜索条件
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto, condition);

        List<Ad> adList = adDao.selectByPage(condition);
        for (Ad ad : adList) {
            AdDto adDtoTemp = new AdDto();
            result.add(adDtoTemp);
            adDtoTemp.setImg(adConfig.getUrl() + ad.getImgFileName());
            BeanUtils.copyProperties(ad,adDtoTemp);
        }
        return result;
    }
}
