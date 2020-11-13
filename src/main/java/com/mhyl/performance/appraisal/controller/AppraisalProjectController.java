package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.domain.entity.AppraisalProject;
import com.mhyl.performance.appraisal.domain.entity.HospitalDepart;
import com.mhyl.performance.appraisal.domain.repository.AppraisalProjectRepo;
import com.mhyl.performance.appraisal.http.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "绩效考核")
@RestController
@RequestMapping("appraisalProject")
public class AppraisalProjectController {
    @Autowired
    private AppraisalProjectRepo appraisalProjectRepo;

    //添加绩效考核项目
    @ApiOperation("添加绩效考核项目")
    @PostMapping("/insert")
    public JsonResult insertAppraisalProject(AppraisalProject appraisalProject){
        appraisalProjectRepo.save(appraisalProject);
        return JsonResult.success("success");
    }

    //绩效考核列表
    @ApiOperation("绩效考核列表")
    @PostMapping("/getList")
    public JsonResult getListAppraisalProject(){
        QueryWrapper<AppraisalProject> appraisalProjectQueryWrapper = new QueryWrapper<>();
        appraisalProjectQueryWrapper.select("id,name,create_time,update_time");
        Map<String, Object> map = appraisalProjectRepo.getMap(appraisalProjectQueryWrapper);
        return JsonResult.success(map);
    }

}
