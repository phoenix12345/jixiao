package com.mhyl.performance.appraisal.config;

import com.mhyl.performance.appraisal.domain.entity.SystemUser;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.http.UserHolder;
import com.mhyl.performance.appraisal.utils.JsonUtils;
import com.mhyl.performance.appraisal.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ArchieDing
 * @since 2020/09/08
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private TokenUtils tokenUtils;
	@Resource
	private AuthProperties properties;

	private PathMatcher matcher = new AntPathMatcher();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		for (String item : properties.getWhiteUrls()) {
			if (matcher.match(item, path)) {
				log.info("{} is in white list.", path);
				return true;
			}
		}

		SystemUser systemUser = tokenUtils.parseToken(request);
		if (systemUser == null) {
			log.warn("request {} without token.", path);
			response.setContentType("application/json; charset=utf-8");
			JsonResult data = JsonResult.error(ThrowException.USER_NOT_LOGIN);
			PrintWriter out = response.getWriter();
			out.print(JsonUtils.toJson(data));
			out.close();
			return false;
		}
		UserHolder.setSystemUser(systemUser);
		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}
		return super.preHandle(request, response, handler);
	}
}
