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
 * 收费项目
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeProject对象", description = "收费项目")
public class ChargeProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateTime;


}
