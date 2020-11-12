package com.mhyl.performance.appraisal.exception;

import com.mhyl.performance.appraisal.http.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 业务异常
	 *
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public Object businessExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error("[GlobalExceptionHandler][businessExceptionHandler] exception", e);
		ServiceException be = (ServiceException) e;
//		String msg = "[MSG:" + be.getResponseEnum().getCode() + "] " + be.getMessage();
		String msg = be.getMessage();
		return JsonResult.error(be.getResponseEnum().getCode(), msg);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Object noHandlerFoundExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error("[GlobalExceptionHandler][noHandlerFoundExceptionHandler] exception", e);
//		String msg = "[MSG:" + ThrowException.PAGE_NOT_FOUND.getCode() + "] " + ThrowException.PAGE_NOT_FOUND.getMessage();
		String msg = ThrowException.PAGE_NOT_FOUND.getMessage();
		return JsonResult.error(ThrowException.PAGE_NOT_FOUND.getCode(), msg);
	}

	/**
	 * 全局异常处理
	 *
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error("[GlobalExceptionHandler][exceptionHandler] exception", e);
//		String msg = "[MSG:" + ThrowException.SERVER_ERROR.getCode() + "] " + ThrowException.SERVER_ERROR.getMessage();
		String msg = ThrowException.SERVER_ERROR.getMessage();
		return JsonResult.error(ThrowException.SERVER_ERROR.getCode(), msg);
	}
}
