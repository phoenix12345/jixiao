package com.mhyl.performance.appraisal.domain.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 科室
 * </p>
 *
 * @author Berger Lan
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="HospitalDepart对象", description="科室")
public class HospitalDepart implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "科室名称")
    private String name;

    @ApiModelProperty(value = "岗位系数总量")
    private BigDecimal jobPostRate;

    @ApiModelProperty(value = "科室系数总量")
    private BigDecimal departRate;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;


}
