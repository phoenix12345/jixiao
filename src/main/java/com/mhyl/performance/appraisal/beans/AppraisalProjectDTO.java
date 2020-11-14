package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * <p>
 * 考核项目
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AppraisalProjectDTO对象", description="考核项目")
public class AppraisalProjectDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "项目名称")
    private String name;

}
