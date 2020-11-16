package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HosptialPostCoefficientDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "医疗费率")
    private BigDecimal medicalRate;

    @ApiModelProperty(value = "当前指导值")
    private BigDecimal guidanceValue;
}
