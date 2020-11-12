package com.mhyl.performance.appraisal.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ArchieDing
 * @since 2020/09/08
 */
@Configuration
public class DruidConfig {
	/**
	 * 注册 Servlet 组件
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		//白名单IP
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		return servletRegistrationBean;
	}

	/**
	 * 注册 Filter 组件
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean statFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		//验证所有请求
		filterRegistrationBean.addUrlPatterns("/*");
		//对 *.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/* 不进行验证
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
