package com.mhyl.performance.appraisal.exception;


/**
 * @author ArchieDing
 * @since 2020/09/01
 */
public class ServiceException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ServiceException(IResponseEnum responseEnum) {
		super(responseEnum, null, responseEnum.getMessage());
	}

	public ServiceException(IResponseEnum responseEnum, Object[] args, String message) {
		super(responseEnum, args, message);
	}

	public ServiceException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
		super(responseEnum, args, message, cause);
	}
}
