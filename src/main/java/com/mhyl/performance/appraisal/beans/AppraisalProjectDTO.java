package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 考核项目
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
public class AppraisalProjectDTO {

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目序号")
    private Integer sorted;
}
