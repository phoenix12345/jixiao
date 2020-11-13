package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.domain.entity.HospitalDoctor;
import com.mhyl.performance.appraisal.domain.repository.HospitalDoctorRepo;
import com.mhyl.performance.appraisal.http.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "医生管理")
@RestController
@RequestMapping("hospitalDoctor")
public class HospitalDoctorController {
    @Autowired
    private HospitalDoctorRepo hospitalDoctorRepo;

    //插入医生
    @ApiOperation("插入医生")
    @PostMapping("/insert")
    public JsonResult insertHospitalDoctor(HospitalDoctor hospitalDoctor){
        QueryWrapper<HospitalDoctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",hospitalDoctor.getName());
        Map<String, Object> map = hospitalDoctorRepo.getMap(queryWrapper);
        if (map.size() > 0){
            return JsonResult.error(500,"姓名重复");
        }
        queryWrapper.clear();
        queryWrapper.eq("id_card",hospitalDoctor.getIdCard());
        map = hospitalDoctorRepo.getMap(queryWrapper);
        if (map.size() > 0){
            return JsonResult.error(500,"身份证号重复");
        }
        hospitalDoctorRepo.save(hospitalDoctor);
        return JsonResult.success("success");
    }

}
