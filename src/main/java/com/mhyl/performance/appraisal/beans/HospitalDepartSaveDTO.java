package com.mhyl.performance.appraisal.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value="HospitalDepartSaveDTO对象", description="科室")
public class HospitalDepartSaveDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "科室名称")
    private String name;

}
