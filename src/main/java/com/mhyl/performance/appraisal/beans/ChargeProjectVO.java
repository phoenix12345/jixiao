package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 收费项目
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
public class ChargeProjectVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "收费项目名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "医疗")
    private BigDecimal medicalCare;

    @ApiModelProperty(value = "护理")
    private BigDecimal nursing;

    @ApiModelProperty(value = "执行科室")
    private BigDecimal executiveDepart;
}
