package com.mhyl.performance.appraisal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
@Getter
@AllArgsConstructor
public enum ThrowException implements ServiceExceptionAssert {
	/**
	 * 系统错误
	 */
	SERVER_ERROR(500, "系统错误"),
	/**
	 * feign错误
	 */
	FEIGN_ERROR(501, "feign错误"),
	/**
	 * 用户未登录
	 */
	USER_NOT_LOGIN(401, "用户身份过期"),
	/**
	 * 参数错误
	 */
	VALID_ERROR(403, "参数错误."),
	/**
	 * 页面不存在
	 */
	PAGE_NOT_FOUND(404, "页面不存在"),
	/**
	 * 参数为空
	 */
	ARG_IS_EMPTY(405, "{0}不能为空"),
	/**
	 * 权限不足
	 */
	PERMISSION_LIMIT(900, "用户权限不足"),
	/**
	 * 此用户不存在，请核对您的用户名
	 */
	USER_NOT_EXISTS(600, "此用户不存在，请核对您的用户名"),
	/**
	 * 用户名或者密码错误，请检查您的用户名与密码，注意标点符号与大小写
	 */
	PASSWORD_NOT_VALIDATE(601, "用户名或者密码错误，请检查您的用户名与密码，注意标点符号与大小写"),
	/**
	 * 与其他科室名称重复，请重新输入
	 */
	DEPART_DUPLICATE(602, "与其他科室名称重复，请重新输入"),
	/**
	 * 收费项目名称与已有名称重复，请复核
	 */
	CHARGE_DUPLICATE(603, "收费项目名称与已有名称重复，请复核"),
	;
	/**
	 * 返回码
	 */
	private int code;
	/**
	 * 返回消息
	 */
	private String message;
}
