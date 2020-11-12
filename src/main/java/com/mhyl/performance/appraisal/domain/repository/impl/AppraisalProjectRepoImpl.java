package com.mhyl.performance.appraisal.domain.repository.impl;

import com.mhyl.performance.appraisal.domain.entity.AppraisalProject;
import com.mhyl.performance.appraisal.domain.mapper.AppraisalProjectMapper;
import com.mhyl.performance.appraisal.domain.repository.AppraisalProjectRepo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考核项目 服务实现类
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Service
public class AppraisalProjectRepoImpl extends ServiceImpl<AppraisalProjectMapper, AppraisalProject> implements AppraisalProjectRepo {

}
