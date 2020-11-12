package com.mhyl.performance.appraisal.exception;

import java.text.MessageFormat;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
public interface ServiceExceptionAssert extends IResponseEnum, Assert {

	/**
	 * 创建异常
	 *
	 * @param args
	 * @return
	 */
	@Override
	default BaseException newException(Object... args) {
		String msg = MessageFormat.format(this.getMessage(), args);
		return new ServiceException(this, args, msg);
	}

	/**
	 * 创建异常
	 *
	 * @param t
	 * @param args
	 * @return
	 */
	@Override
	default BaseException newException(Throwable t, Object... args) {
		String msg = MessageFormat.format(this.getMessage(), args);

		return new ServiceException(this, args, msg, t);
	}

}
