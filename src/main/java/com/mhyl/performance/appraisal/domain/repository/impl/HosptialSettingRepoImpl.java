package com.mhyl.performance.appraisal.domain.repository.impl;

import com.mhyl.performance.appraisal.domain.entity.HosptialSetting;
import com.mhyl.performance.appraisal.domain.mapper.HosptialSettingMapper;
import com.mhyl.performance.appraisal.domain.repository.HosptialSettingRepo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医院设置 服务实现类
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Service
public class HosptialSettingRepoImpl extends ServiceImpl<HosptialSettingMapper, HosptialSetting> implements HosptialSettingRepo {

}
