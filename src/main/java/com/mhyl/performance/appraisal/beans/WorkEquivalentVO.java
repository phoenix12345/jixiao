package com.mhyl.performance.appraisal.beans;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ExcelTarget("workEquivalentVO")
public class WorkEquivalentVO implements java.io.Serializable {

    @ApiModelProperty(value = "项目名称")
    @Excel(name = "项目名称", orderNum = "1")
    private String appraisalProjectName;

    @ApiModelProperty(value = "项目序号")
    @Excel(name = "项目序号", orderNum = "2")
    private Integer sorted;

    @ApiModelProperty(value = "服务名称")
    @Excel(name = "服务名称", orderNum = "3")
    private String serviceProjectName;

    @ApiModelProperty(value = "单位")
    @Excel(name = "单位", orderNum = "4")
    private String unit;

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注", orderNum = "5")
    private String remark;

    @ApiModelProperty(value = "工作量")
    @Excel(name = "工作量", orderNum = "6")
    private BigDecimal jobContent;

    @ApiModelProperty(value = "技术含量")
    @Excel(name = "技术含量", orderNum = "7")
    private BigDecimal technicalContent;

    @ApiModelProperty(value = "技术难度")
    @Excel(name = "技术难度", orderNum = "8")
    private BigDecimal technicalDifficulty;

    @ApiModelProperty(value = "工作风险")
    @Excel(name = "工作风险", orderNum = "9")
    private BigDecimal jobRisk;

    @ApiModelProperty(value = "单位当量值")
    @Excel(name = "单位当量值", orderNum = "10")
    private BigDecimal unitEquivalent;

}
