package com.mhyl.performance.appraisal.controller;

import com.mhyl.performance.appraisal.beans.HospitalPostCoefficientDTO;
import com.mhyl.performance.appraisal.beans.HospitalSettingVO;
import com.mhyl.performance.appraisal.beans.HospitalWorkEquivalentDTO;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.service.HosptialSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "医院配置")
@RestController
@RequestMapping("/hospitalSetting")
public class HospitalSettingController {

    @Autowired
    private HosptialSettingService hosptialSettingService;

    @ApiOperation("获取医院配置信息")
    @GetMapping("/getHospitalSetting")
    public JsonResult<HospitalSettingVO> getHospitalSetting() {
        HospitalSettingVO data = hosptialSettingService.getHospitalSetting();
        return JsonResult.success(data);
    }

    @ApiOperation("修改医院工作当量标准")
    @PostMapping("/updateWorkEquivalent")
    public JsonResult<String> updateWorkEquivalent(@RequestBody HospitalWorkEquivalentDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "主键");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobContentPercent(), "工作量系数");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getTechnicalContentPercent(), "技术含量系数");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getTechnicalDifficultyPercent(), "技术难度系数");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobRiskPercent(), "工作风险系数");
        hosptialSettingService.updateWorkEquivalent(dto);
        return JsonResult.success("success");
    }

    @ApiOperation("修改医院医疗费率、当量指导值")
    @PostMapping("/updatePostCoefficient")
    public JsonResult<String> updatePostCoefficient(@RequestBody HospitalPostCoefficientDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "主键");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getMedicalRate(), "医疗费率");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getGuidanceValue(), "当前指导值");
        hosptialSettingService.updatePostCoefficient(dto);
        return JsonResult.success("success");
    }


}
