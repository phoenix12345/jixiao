package com.mhyl.performance.appraisal.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 服务项目
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ServiceProject对象", description = "服务项目")
public class ServiceProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所属项目")
    private Long appraisalId;

    @ApiModelProperty(value = "服务名称")
    private String name;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateTime;


}
