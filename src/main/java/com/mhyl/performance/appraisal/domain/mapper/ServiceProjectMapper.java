package com.mhyl.performance.appraisal.domain.mapper;

import com.mhyl.performance.appraisal.beans.WorkEquivalentVO;
import com.mhyl.performance.appraisal.domain.entity.ServiceProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务项目 Mapper 接口
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
public interface ServiceProjectMapper extends BaseMapper<ServiceProject> {

    List<WorkEquivalentVO> selectByAppraisalIds(@Param("ids") List<Integer> ids);
}
