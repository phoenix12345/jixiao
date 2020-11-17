package com.mhyl.performance.appraisal.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mhyl.performance.appraisal.beans.WorkEquivalentDTO;
import com.mhyl.performance.appraisal.beans.WorkEquivalentVO;
import com.mhyl.performance.appraisal.domain.entity.ServiceProject;
import com.mhyl.performance.appraisal.domain.mapper.ServiceProjectMapper;
import com.mhyl.performance.appraisal.domain.repository.ServiceProjectRepo;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkEquivalentService {

    @Autowired
    private ServiceProjectRepo serviceProjectRepo;
    @Resource
    private ServiceProjectMapper serviceProjectMapper;

    /**
     * 更新某个考核项目下服务项目的工作当量
     *
     * @param list
     * @return
     */
    public JsonResult<String> updateListByAppraisalId(List<WorkEquivalentDTO> list) {
        list.forEach(dto -> {
            ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getAppraisalId(), "考核项目ID");
            ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "服务项目ID");
            ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobContent(), "工作量");
            ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getTechnicalContent(), "技术含量");
            ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getTechnicalDifficulty(), "技术难度");
            ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobRisk(), "工作风险");
            ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getUnitEquivalent(), "单位当量值");
            ServiceProject serviceProject = BeanMapper.map(dto, ServiceProject.class);
            UpdateWrapper<ServiceProject> wrapper = new UpdateWrapper();
            wrapper.eq("appraisal_id", dto.getAppraisalId());
            wrapper.eq("id", dto.getId());
            serviceProjectRepo.update(serviceProject, wrapper);
        });
        return JsonResult.success();
    }

    /**
     * 批量查询考核项目下服务项目的工作当量
     *
     * @param appraisalIds
     * @return
     */
    public List<WorkEquivalentVO> queryDataByAppraisalIds(String appraisalIds) {
        List<Integer> ids = Arrays.asList(appraisalIds.split(",")).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        List<WorkEquivalentVO> data = serviceProjectMapper.selectByAppraisalIds(ids);
        return data;
    }
}
