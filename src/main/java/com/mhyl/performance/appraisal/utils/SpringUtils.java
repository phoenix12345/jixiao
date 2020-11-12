package com.mhyl.performance.appraisal.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ArchieDing
 * @since 2020/09/03
 */
@Component
public class SpringUtils implements ApplicationContextAware {
	/**
	 * Spring应用上下文
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 获取ApplicationContext
	 *
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtils.applicationContext = applicationContext;
	}

	/**
	 * 按名称获取spring bean实例
	 *
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	/**
	 * 按类型获取spring bean 实例
	 *
	 * @param beanType
	 * @return
	 */
	public static <T> T getBean(Class<T> beanType) {
		return applicationContext.getBean(beanType);
	}
}
