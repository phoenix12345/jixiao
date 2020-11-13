package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.domain.entity.ChargeProject;
import com.mhyl.performance.appraisal.domain.repository.ChargeProjectRepo;
import com.mhyl.performance.appraisal.http.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "收费项目")
@RestController
@RequestMapping("chargeProject")
public class ChargeProjectController {
    @Autowired
    private ChargeProjectRepo chargeProjectRepo;

    //添加收费项目
    @ApiOperation("添加收费项目")
    @PostMapping("/insert")
    public JsonResult<String> insertChargeProject(ChargeProject chargeProject){
        chargeProjectRepo.save(chargeProject);
        return JsonResult.success("success");
    }

    //修改收费项目
    @ApiOperation("修改收费项目")
    @PostMapping("/update")
    public JsonResult<String> updateChargeProject(ChargeProject chargeProject){
        chargeProjectRepo.updateById(chargeProject);
        return JsonResult.success("success");
    }

    //获取收费项目列表
    @ApiOperation("获取收费项目列表")
    @PostMapping("/getList")
    public JsonResult getListChargeProject(){
        QueryWrapper<ChargeProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,name,remark");
        Map<String, Object> map = chargeProjectRepo.getMap(queryWrapper);
        return JsonResult.success(map);
    }
}
