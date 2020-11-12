package com.mhyl.performance.appraisal.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.domain.entity.SystemUser;
import com.mhyl.performance.appraisal.domain.mapper.SystemUserMapper;
import com.mhyl.performance.appraisal.http.TokenConstant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ArchieDing
 * @since 2020/09/08
 */
@Component
public class TokenUtils {
	@Resource
	private SystemUserMapper systemUserMapper;

	public String createToken() {
		return IdUtil.simpleUUID();
	}

	public SystemUser parseToken(HttpServletRequest request) {
		String token = request.getParameter(TokenConstant.TOKEN_HEADER_KEY);
		if (StrUtil.isEmpty(token)) {
			token = request.getHeader(TokenConstant.TOKEN_HEADER_KEY);
		}
		if (StrUtil.isEmpty(token)) {
			return null;
		}
		QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();
		wrapper.eq("token", token);
		wrapper.last("limit 1");
		return systemUserMapper.selectOne(wrapper);
	}
}
