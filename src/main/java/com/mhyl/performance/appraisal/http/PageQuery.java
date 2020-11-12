package com.mhyl.performance.appraisal.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description //TODO
 * @Author Qi Ding
 * @Date 2020/11/6 2:04 下午
 * @Version 1.0
 */
@Data
public class PageQuery implements Serializable {
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageNum;
    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;
}
