package com.mhyl.performance.appraisal.domain.repository.impl;

import com.mhyl.performance.appraisal.domain.entity.ChargeProject;
import com.mhyl.performance.appraisal.domain.mapper.ChargeProjectMapper;
import com.mhyl.performance.appraisal.domain.repository.ChargeProjectRepo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收费项目 服务实现类
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Service
public class ChargeProjectRepoImpl extends ServiceImpl<ChargeProjectMapper, ChargeProject> implements ChargeProjectRepo {

}
