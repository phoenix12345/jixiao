package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ServiceProjectUpdateDTO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "所属项目")
    private Long appraisalId;

    @ApiModelProperty(value = "服务名称")
    private String name;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "备注")
    private String remark;
}
