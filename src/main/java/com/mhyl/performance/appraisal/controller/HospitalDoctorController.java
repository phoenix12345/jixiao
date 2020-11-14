package com.mhyl.performance.appraisal.controller;

import com.mhyl.performance.appraisal.beans.HospitalDoctorDTO;
import com.mhyl.performance.appraisal.domain.entity.HospitalDoctor;
import com.mhyl.performance.appraisal.domain.repository.HospitalDoctorRepo;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "医生管理")
@RestController
@RequestMapping("hospitalDoctor")
public class HospitalDoctorController {
    @Autowired
    private HospitalDoctorRepo hospitalDoctorRepo;

    @ApiOperation("添加员工到对应科室")
    @PostMapping("/save")
    public JsonResult save(@RequestBody  HospitalDoctorDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "科室名称");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getIdCard(), "身份证号码");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobTitle(), "职称");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobPost(), "职务");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getDepartId(), "所在科室");
        HospitalDoctor hospitalDoctor = BeanMapper.map(dto, HospitalDoctor.class);
        hospitalDoctorRepo.save(hospitalDoctor);
        return JsonResult.success("success");
    }

    @ApiOperation("修改医生信息")
    @PostMapping("/update")
    public JsonResult update(@RequestBody  HospitalDoctorDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "医生id");
        HospitalDoctor hospitalDoctor = BeanMapper.map(dto, HospitalDoctor.class);
        hospitalDoctorRepo.updateById(hospitalDoctor);
        return JsonResult.success("success");
    }

}
