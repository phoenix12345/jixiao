package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.domain.entity.ServiceProject;
import com.mhyl.performance.appraisal.domain.repository.ServiceProjectRepo;
import com.mhyl.performance.appraisal.http.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Api(tags = "服务项目")
@RestController
@RequestMapping("serviceProject")
public class ServiceProjectController {
    @Autowired
    private ServiceProjectRepo serviceProjectRepo;

    //添加服务项目到考核项目
    @ApiOperation("添加服务项目到考核项目")
    @PostMapping("/insert")
    public JsonResult insertServiceProject(ServiceProject serviceProject){
        serviceProjectRepo.save(serviceProject);
        return JsonResult.success("success");
    }

    //修改服务项目
    @ApiOperation("修改服务项目")
    @PostMapping("/update")
    public JsonResult updateServiceProject(ServiceProject serviceProject){
        serviceProjectRepo.updateById(serviceProject);
        return JsonResult.success("success");
    }

    //获取某考核项目下的服务项目列表
    @ApiOperation("获取当前考核项目下的服务项目列表")
    @PostMapping("/getListByAppraisalId")
    public JsonResult getListByIdServiceProject(ServiceProject serviceProject){
        QueryWrapper<ServiceProject> serviceProjectQueryWrapper = new QueryWrapper<>();
        serviceProjectQueryWrapper.select("id,name,unit,remark");
        serviceProjectQueryWrapper.eq("appraisal_id",serviceProject.getAppraisalId());
        Map<String, Object> map = serviceProjectRepo.getMap(serviceProjectQueryWrapper);
        return JsonResult.success(map);
    }
}
