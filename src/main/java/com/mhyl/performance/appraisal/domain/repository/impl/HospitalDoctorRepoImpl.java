package com.mhyl.performance.appraisal.domain.repository.impl;

import com.mhyl.performance.appraisal.domain.entity.HospitalDoctor;
import com.mhyl.performance.appraisal.domain.mapper.HospitalDoctorMapper;
import com.mhyl.performance.appraisal.domain.repository.HospitalDoctorRepo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生 服务实现类
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Service
public class HospitalDoctorRepoImpl extends ServiceImpl<HospitalDoctorMapper, HospitalDoctor> implements HospitalDoctorRepo {

}
