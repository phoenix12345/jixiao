package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description //TODO
 * @Author Qi Ding
 * @Date 2020/11/12 8:21 下午
 * @Version 1.0
 */
@Data
public class LoginDTO {
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;
}
