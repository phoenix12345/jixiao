package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

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
@ApiModel(value="HospitalDoctorSaveDTO对象", description="医生")
public class HospitalDoctorSaveDTO implements Serializable {

    private static final long serialVersionUID=1L;

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

}
