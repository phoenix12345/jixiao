package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WorkEquivalentDTO {

    @ApiModelProperty(value = "所属项目")
    private Long appraisalId;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "工作量")
    private BigDecimal jobContent;

    @ApiModelProperty(value = "技术含量")
    private BigDecimal technicalContent;

    @ApiModelProperty(value = "技术难度")
    private BigDecimal technicalDifficulty;

    @ApiModelProperty(value = "工作风险")
    private BigDecimal jobRisk;

    @ApiModelProperty(value = "单位当量值")
    private BigDecimal unitEquivalent;

}
