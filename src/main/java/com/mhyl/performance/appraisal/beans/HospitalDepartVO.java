package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 科室
 * </p>
 *
 * @author Berger Lan
 * @since 2020-11-13
 */
@Data
public class HospitalDepartVO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "科室名称")
    private String name;

    @ApiModelProperty(value = "岗位系数总量")
    private BigDecimal jobPostRate;

    @ApiModelProperty(value = "科室系数总量")
    private BigDecimal departRate;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}
