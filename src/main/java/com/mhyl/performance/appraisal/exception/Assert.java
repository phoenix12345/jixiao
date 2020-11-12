package com.mhyl.performance.appraisal.exception;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.Objects;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
public interface Assert {
	/**
	 * 创建异常
	 *
	 * @param args
	 * @return
	 */
	BaseException newException(Object... args);

	/**
	 * 创建异常
	 *
	 * @param t
	 * @param args
	 * @return
	 */
	BaseException newException(Throwable t, Object... args);

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 *
	 * @param obj 待判断对象
	 */
	default void ifNull(Object obj) {
		if (obj == null) {
			throw newException(obj);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param obj  待判断对象
	 * @param args message占位符对应的参数列表
	 */
	default void ifNull(Object obj, Object... args) {
		if (obj == null) {
			throw newException(args);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 *
	 * @param obj   待判断对象
	 * @param eqObj 待判断对象
	 */
	default void ifNotEquals(Object obj, Object eqObj) {
		if (!Objects.equals(obj, eqObj)) {
			throw newException(obj);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param obj   待判断对象
	 * @param eqObj 待判断对象
	 * @param args  message占位符对应的参数列表
	 */
	default void ifNotEquals(Object obj, Object eqObj, Object... args) {
		if (!Objects.equals(obj, eqObj)) {
			throw newException(args);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 *
	 * @param obj 待判断对象
	 */
	default void ifEmpty(Object obj) {
		if (ObjectUtil.isEmpty(obj)) {
			throw newException(obj);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param obj  待判断对象
	 * @param args message占位符对应的参数列表
	 */
	default void ifEmpty(Object obj, Object... args) {
		if (ObjectUtil.isEmpty(obj)) {
			throw newException(args);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 *
	 * @param obj   待判断对象
	 * @param eqObj 待判断对象
	 */
	default void ifEquals(Object obj, Object eqObj) {
		if (Objects.equals(obj, eqObj)) {
			throw newException(obj);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param obj   待判断对象
	 * @param eqObj 待判断对象
	 * @param args  message占位符对应的参数列表
	 */
	default void ifEquals(Object obj, Object eqObj, Object... args) {
		if (Objects.equals(obj, eqObj)) {
			throw newException(args);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 *
	 * @param obj 待判断对象
	 * @param num 待判断对象
	 */
	default void ifGreaterThen(Long obj, Long num) {
		if (obj > num) {
			throw newException(obj);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param obj  待判断对象
	 * @param num  待判断对象
	 * @param args message占位符对应的参数列表
	 */
	default void ifGreaterThen(Long obj, Long num, Object... args) {
		if (obj > num) {
			throw newException(args);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 *
	 * @param obj 待判断对象
	 * @param num 待判断对象
	 */
	default void ifGreaterThen(Integer obj, Integer num) {
		if (obj > num) {
			throw newException(obj);
		}
	}

	/**
	 * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
	 * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param obj  待判断对象
	 * @param num  待判断对象
	 * @param args message占位符对应的参数列表
	 */
	default void ifGreaterThen(Integer obj, Integer num, Object... args) {
		if (obj > num) {
			throw newException(args);
		}
	}

	/**
	 * 不是数值
	 *
	 * @param num
	 * @param args
	 */
	default void ifNotNumber(String num, Object... args) {
		if (!NumberUtil.isNumber(num)) {
			throw newException(args);
		}
	}
}
