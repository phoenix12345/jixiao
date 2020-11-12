package com.mhyl.performance.appraisal.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description //TODO
 * @Author Qi Ding
 * @Date 2020/11/12 7:47 下午
 * @Version 1.0
 */
@Data
public class LoginUserVO {

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("登陆令牌")
    private String token;
}
