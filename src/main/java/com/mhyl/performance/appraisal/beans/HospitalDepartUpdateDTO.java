package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 科室
 * </p>
 *
 * @author Berger Lan
 * @since 2020-11-13
 */
@Data
public class HospitalDepartUpdateDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "科室名称")
    private String name;
}
