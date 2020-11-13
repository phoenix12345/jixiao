package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.domain.entity.HospitalDepart;
import com.mhyl.performance.appraisal.domain.repository.HospitalDepartRepo;
import com.mhyl.performance.appraisal.http.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@Api(tags = "医院科室")
@RestController
@RequestMapping("hospitalDepart")
public class HospitalDepartController {
    @Autowired
    private HospitalDepartRepo hospitalDepartRepo;

    //增加科室
    @ApiOperation("增加科室")
    @PostMapping("/insert")
    public JsonResult insertHospitalDepart(HospitalDepart hospitalDepart){
        hospitalDepartRepo.save(hospitalDepart);
        return JsonResult.success("success");
    }

    //更新科室
    @ApiOperation("更新科室")
    @PostMapping("/update")
    public JsonResult updateHospitalDepart(HospitalDepart hospitalDepart){
        hospitalDepartRepo.updateById(hospitalDepart);
        return JsonResult.success();
    }

    //删除科室
    @ApiOperation("删除科室")
    @PostMapping("/delete")
    public JsonResult deleteHospitalDepart(String id){
        hospitalDepartRepo.removeById(id);
        return JsonResult.success();
    }

    //科室列表
    @ApiOperation("科室列表")
    @PostMapping("/getList")
    public JsonResult getListHospitalDepart(){
        QueryWrapper<HospitalDepart> hospitalDepartQueryWrapper = new QueryWrapper<>();
        hospitalDepartQueryWrapper.select("id,name,job_post_rate,depart_rate,create_time,update_time");
        Map<String, Object> map = hospitalDepartRepo.getMap(hospitalDepartQueryWrapper);
        return JsonResult.success(map);
    }
}
