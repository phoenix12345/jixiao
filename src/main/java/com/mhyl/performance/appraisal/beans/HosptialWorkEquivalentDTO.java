package com.mhyl.performance.appraisal.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HosptialWorkEquivalentDTO {

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
}
