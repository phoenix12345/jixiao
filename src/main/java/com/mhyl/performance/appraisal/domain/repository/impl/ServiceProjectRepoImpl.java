package com.mhyl.performance.appraisal.domain.repository.impl;

import com.mhyl.performance.appraisal.domain.entity.ServiceProject;
import com.mhyl.performance.appraisal.domain.mapper.ServiceProjectMapper;
import com.mhyl.performance.appraisal.domain.repository.ServiceProjectRepo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务项目 服务实现类
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Service
public class ServiceProjectRepoImpl extends ServiceImpl<ServiceProjectMapper, ServiceProject> implements ServiceProjectRepo {

}
