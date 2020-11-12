package com.mhyl.performance.appraisal.domain.repository.impl;

import com.mhyl.performance.appraisal.domain.entity.SystemUser;
import com.mhyl.performance.appraisal.domain.mapper.SystemUserMapper;
import com.mhyl.performance.appraisal.domain.repository.SystemUserRepo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Service
public class SystemUserRepoImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserRepo {

}
