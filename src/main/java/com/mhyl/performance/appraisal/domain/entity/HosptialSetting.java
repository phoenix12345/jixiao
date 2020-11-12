package com.mhyl.performance.appraisal.domain.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 医院设置
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="HosptialSetting对象", description="医院设置")
public class HosptialSetting implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
      @TableId(value = "id", type = IdType.AUTO)
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
