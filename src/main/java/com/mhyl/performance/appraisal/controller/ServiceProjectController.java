package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.ServiceProjectSaveDTO;
import com.mhyl.performance.appraisal.beans.ServiceProjectUpdateDTO;
import com.mhyl.performance.appraisal.beans.ServiceProjectVO;
import com.mhyl.performance.appraisal.domain.entity.ServiceProject;
import com.mhyl.performance.appraisal.domain.repository.ServiceProjectRepo;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "服务项目")
@RestController
@RequestMapping("/serviceProject")
public class ServiceProjectController {
    @Autowired
    private ServiceProjectRepo serviceProjectRepo;

    @ApiOperation("添加服务项目到考核项目")
    @PostMapping("/save")
    public JsonResult save(@RequestBody ServiceProjectSaveDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getAppraisalId(), "所属项目id");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "服务名称");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getUnit(), "服务单位");
        checkServiceName(dto.getAppraisalId(), dto.getName());
        ServiceProject serviceProject = BeanMapper.map(dto, ServiceProject.class);
        serviceProjectRepo.save(serviceProject);
        return JsonResult.success("success");
    }

    private void checkServiceName(Long appraisalId, String name) {
        QueryWrapper<ServiceProject> wrapper = new QueryWrapper<>();
        wrapper.eq("appraisal_id", appraisalId);
        wrapper.eq("name", name);
        int count = serviceProjectRepo.count(wrapper);
        ThrowException.SERVICE_DUPLICATE.ifNotEquals(count, 0);
    }

    @ApiOperation("修改服务项目")
    @PostMapping("/update")
    public JsonResult<String> update(@RequestBody ServiceProjectUpdateDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "项目id");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getAppraisalId(), "所属项目id");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "服务名称");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getUnit(), "服务单位");
        checkServiceName(dto.getAppraisalId(), dto.getName());
        ServiceProject serviceProject = BeanMapper.map(dto, ServiceProject.class);
        serviceProjectRepo.updateById(serviceProject);
        return JsonResult.success("success");
    }

    @ApiOperation("获取当前考核项目下的服务项目列表")
    @GetMapping("/list")
    public JsonResult<List<ServiceProjectVO>> list(@RequestParam Long appraisalId) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(appraisalId, "考核项目id");
        QueryWrapper<ServiceProject> wrapper = new QueryWrapper<>();
        wrapper.eq("appraisal_id", appraisalId);
        List<ServiceProject> list = serviceProjectRepo.list(wrapper);
        List<ServiceProjectVO> data = BeanMapper.mapList(list, ServiceProject.class, ServiceProjectVO.class);
        return JsonResult.success(data);
    }
}
