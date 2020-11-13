package com.mhyl.performance.appraisal.beans;

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
import java.time.LocalDateTime;

/**
 * <p>
 * 医生
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="HospitalDoctor对象", description="医生")
public class HospitalDoctorDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "职称")
    private String jobTitle;

    @ApiModelProperty(value = "职务")
    private String jobPost;

    @ApiModelProperty(value = "所在科室")
    private Long departId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "院内岗位系数")
    private BigDecimal jobPostRate;

    @ApiModelProperty(value = "科室分配系数")
    private BigDecimal departRate;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "0: 职务+职称 1:职务 2:职称 3:无")
    private Integer sorted;


}