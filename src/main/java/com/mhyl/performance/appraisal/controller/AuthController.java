package com.mhyl.performance.appraisal.controller;

import com.mhyl.performance.appraisal.beans.LoginDTO;
import com.mhyl.performance.appraisal.beans.LoginUserVO;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.service.SystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description //TODO
 * @Author Qi Ding
 * @Date 2020/11/12 7:46 下午
 * @Version 1.0
 */
@Api(tags = "登陆")
@RestController
public class AuthController {
    @Autowired
    private SystemUserService systemUserService;

    @ApiOperation("登陆接口")
    @PostMapping("/login")
    public JsonResult<LoginUserVO> login(@RequestBody LoginDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getAccount(), "账号");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getPassword(), "密码");
        LoginUserVO vo = systemUserService.login(dto);
        return JsonResult.success(vo);
    }
}
