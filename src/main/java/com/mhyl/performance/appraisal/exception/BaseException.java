package com.mhyl.performance.appraisal.exception;

import lombok.Getter;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
@Getter
public class BaseException extends RuntimeException {
	private IResponseEnum responseEnum;
	private Object[] args;
	private String message;

	public BaseException(IResponseEnum responseEnum) {
		this.responseEnum = responseEnum;
	}

	public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
		super(message);
		this.responseEnum = responseEnum;
		this.args = args;
		this.message = message;
	}

	public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
		super(message, cause);
		this.responseEnum = responseEnum;
		this.args = args;
		this.message = message;
	}
}
