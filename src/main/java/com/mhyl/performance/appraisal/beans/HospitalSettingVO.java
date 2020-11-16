package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HospitalSettingVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "工作量系数")
    private Integer jobContentPercent;

    @ApiModelProperty(value = "技术含量系数")
    private Integer technicalContentPercent;

    @ApiModelProperty(value = "技术难度系数")
    private Integer technicalDifficultyPercent;

    @ApiModelProperty(value = "工作风险系数")
    private Integer jobRiskPercent;

    @ApiModelProperty(value = "医疗费率")
    private BigDecimal medicalRate;

    @ApiModelProperty(value = "当前指导值")
    private BigDecimal guidanceValue;
}
