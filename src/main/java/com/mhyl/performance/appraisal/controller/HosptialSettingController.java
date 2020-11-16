package com.mhyl.performance.appraisal.controller;

import com.mhyl.performance.appraisal.beans.HosptialPostCoefficientDTO;
import com.mhyl.performance.appraisal.beans.HosptialSettingVO;
import com.mhyl.performance.appraisal.beans.HosptialWorkEquivalentDTO;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.service.HosptialSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "医院配置")
@RestController
@RequestMapping("/hosptialSetting")
public class HosptialSettingController {

    @Autowired
    private HosptialSettingService hosptialSettingService;

    @ApiOperation("获取医院配置信息")
    @GetMapping("/getHosptialSetting")
    public JsonResult getHosptialSetting() {
        HosptialSettingVO data = hosptialSettingService.getHosptialSetting();
        return JsonResult.success(data);
    }

    @ApiOperation("修改医院工作当量标准")
    @PostMapping("/updateWorkEquivalent")
    public JsonResult updateWorkEquivalent(@RequestBody HosptialWorkEquivalentDTO hosptialWorkEquivalentDTO) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialWorkEquivalentDTO.getId(), "主键");
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialWorkEquivalentDTO.getJobContentPercent(), "工作量系数");
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialWorkEquivalentDTO.getTechnicalContentPercent(), "技术含量系数");
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialWorkEquivalentDTO.getTechnicalDifficultyPercent(), "技术难度系数");
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialWorkEquivalentDTO.getJobRiskPercent(), "工作风险系数");
        hosptialSettingService.updateWorkEquivalent(hosptialWorkEquivalentDTO);
        return JsonResult.success();
    }

    @ApiOperation("修改医院医疗费率、当量指导值")
    @PostMapping("/updatePostCoefficient")
    public JsonResult updatePostCoefficient(@RequestBody HosptialPostCoefficientDTO hosptialPostCoefficientDTO) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialPostCoefficientDTO.getId(), "主键");
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialPostCoefficientDTO.getMedicalRate(), "医疗费率");
        ThrowException.ARG_IS_EMPTY.ifEmpty(hosptialPostCoefficientDTO.getGuidanceValue(), "当前指导值");
        hosptialSettingService.updatePostCoefficient(hosptialPostCoefficientDTO);
        return JsonResult.success();
    }


}
