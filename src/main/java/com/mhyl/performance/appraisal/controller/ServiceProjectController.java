package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.ServiceProjectDTO;
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

    //添加服务项目到考核项目
    @ApiOperation("添加服务项目到考核项目")
    @PostMapping("/save")
    public JsonResult save(@RequestBody ServiceProjectDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "服务名称");
        QueryWrapper<ServiceProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.eq("name",dto.getName());
        List<ServiceProject> list = serviceProjectRepo.list(queryWrapper);
        if (list.size() > 0){
            return JsonResult.error(500,"与已有服务项目名称重复，请复核");
        }
        ServiceProject serviceProject = BeanMapper.map(dto, ServiceProject.class);
        serviceProjectRepo.save(serviceProject);
        return JsonResult.success("success");
    }

    //修改服务项目
    @ApiOperation("修改服务项目")
    @PostMapping("/update")
    public JsonResult<String> update(@RequestBody ServiceProjectDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getAppraisalId(), "服务项目id");
        QueryWrapper<ServiceProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.eq("name",dto.getName());
        List<ServiceProject> list = serviceProjectRepo.list(queryWrapper);
        if (list.size() > 0){
            return JsonResult.error(500,"与已有服务项目名称重复，请复核");
        }
        ServiceProject serviceProject = BeanMapper.map(dto, ServiceProject.class);
        serviceProjectRepo.updateById(serviceProject);
        return JsonResult.success("success");
    }

    //获取某考核项目下的服务项目列表

    @ApiOperation("获取当前考核项目下的服务项目列表")
    @GetMapping("/getList")
    public JsonResult<List<ServiceProjectVO>> getList(@RequestParam Long appraisalId){
        QueryWrapper<ServiceProject> wrapper = new QueryWrapper<>();
        ThrowException.ARG_IS_EMPTY.ifEmpty(appraisalId, "考核项目id");
        wrapper.eq("appraisal_id", appraisalId);
        List<ServiceProject> list = serviceProjectRepo.list(wrapper);
        List<ServiceProjectVO> data = BeanMapper.mapList(list, ServiceProject.class, ServiceProjectVO.class);
        return JsonResult.success(data);
    }
}
