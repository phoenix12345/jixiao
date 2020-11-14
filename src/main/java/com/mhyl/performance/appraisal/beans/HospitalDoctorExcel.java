package com.mhyl.performance.appraisal.beans;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class HospitalDoctorExcel implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Excel(name = "*姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

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
      @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "0: 职务+职称 1:职务 2:职称 3:无")
    private Integer sorted;


}
