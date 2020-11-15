package com.mhyl.performance.appraisal.beans;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 医生
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
public class HospitalDoctorExcel {
    @ApiModelProperty(value = "主键")
    private Long id;

    @Excel(name = "*姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "0: 职务+职称 1:职务 2:职称 3:无")
    private Integer sorted;

    @Excel(name = "*身份证号码")
    @ApiModelProperty(value = "身份证")
    private String idCard;

    @Excel(name = "*职称")
    @ApiModelProperty(value = "职称")
    private String jobTitle;

    @Excel(name = "*职务")
    @ApiModelProperty(value = "职务")
    private String jobPost;

    @Excel(name = "*所在科室")
    @ApiModelProperty(value = "所在科室")
    private Long departId;

    @Excel(name = "备注")
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "院内岗位系数")
    private BigDecimal jobPostRate;

    @ApiModelProperty(value = "科室分配系数")
    private BigDecimal departRate;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "修改时间")
    private Long updateTime;
}
