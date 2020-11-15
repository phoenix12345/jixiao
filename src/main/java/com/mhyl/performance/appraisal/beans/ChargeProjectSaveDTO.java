package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 收费项目
 * </p>
 *
 * @author Archie Ding
 * @since 2020-11-12
 */
@Data
public class ChargeProjectSaveDTO {
    @ApiModelProperty(value = "收费项目名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
