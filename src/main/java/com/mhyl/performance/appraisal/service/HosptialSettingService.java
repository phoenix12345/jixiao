package com.mhyl.performance.appraisal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.HospitalPostCoefficientDTO;
import com.mhyl.performance.appraisal.beans.HospitalSettingVO;
import com.mhyl.performance.appraisal.beans.HospitalWorkEquivalentDTO;
import com.mhyl.performance.appraisal.domain.entity.HosptialSetting;
import com.mhyl.performance.appraisal.domain.repository.HosptialSettingRepo;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HosptialSettingService {

    @Autowired
    private HosptialSettingRepo hosptialSettingRepo;

    /**
     * 获取医院配置信息
     *
     * @return
     */
    public HospitalSettingVO getHospitalSetting() {
        QueryWrapper<HosptialSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1");
        HosptialSetting hosptialSetting = hosptialSettingRepo.getOne(queryWrapper, false);
        return BeanMapper.map(hosptialSetting, HospitalSettingVO.class);
    }

    /**
     * 修改医院工作当量标准
     *
     * @param dto
     */
    public void updateWorkEquivalent(HospitalWorkEquivalentDTO dto) {
        HosptialSetting hosptialSetting = BeanMapper.map(dto, HosptialSetting.class);
        hosptialSettingRepo.updateById(hosptialSetting);
    }

    /**
     * 修改医院医疗费率、当量指导值
     *
     * @param dto
     */
    public void updatePostCoefficient(HospitalPostCoefficientDTO dto) {
        HosptialSetting hosptialSetting = BeanMapper.map(dto, HosptialSetting.class);
        hosptialSettingRepo.updateById(hosptialSetting);
    }
}
