package com.mhyl.performance.appraisal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.LoginDTO;
import com.mhyl.performance.appraisal.beans.LoginUserVO;
import com.mhyl.performance.appraisal.domain.entity.SystemUser;
import com.mhyl.performance.appraisal.domain.repository.SystemUserRepo;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import com.mhyl.performance.appraisal.utils.PwdUtils;
import com.mhyl.performance.appraisal.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description //TODO
 * @Author Qi Ding
 * @Date 2020/11/12 7:39 下午
 * @Version 1.0
 */
@Component
public class SystemUserService {
    @Autowired
    private SystemUserRepo systemUserRepo;
    @Autowired
    private TokenUtils tokenUtils;

    public LoginUserVO login(LoginDTO dto) {
        QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();
        wrapper.eq("account", dto.getAccount());
        SystemUser systemUser = systemUserRepo.getOne(wrapper, false);
        // 用户不存在
        ThrowException.USER_NOT_EXISTS.ifNull(systemUser);
        boolean success = PwdUtils.checkPassword(systemUser.getPassword(), dto.getPassword(), systemUser.getSalt());
        // 用户名或密码错误
        ThrowException.PASSWORD_NOT_VALIDATE.ifNotEquals(success, true);
        // 设置登陆令牌
        systemUser.setToken(tokenUtils.createToken());
        systemUserRepo.updateById(systemUser);
        return BeanMapper.map(systemUser, LoginUserVO.class);
    }
}
