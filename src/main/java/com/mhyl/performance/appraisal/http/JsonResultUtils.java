package com.mhyl.performance.appraisal.http;


import com.mhyl.performance.appraisal.exception.IResponseEnum;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
public class JsonResultUtils {
	/**
	 * 返回成功，传入返回体具体出參
	 *
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> JsonResult<T> success(T t) {
		JsonResult<T> result = new JsonResult<T>();
		result.setCode(200);
		result.setMsg("success");
		result.setData(t);
		return result;
	}

	/**
	 * 提供给部分不需要出參的接口
	 *
	 * @return
	 */
	public static JsonResult success() {
		return success(null);
	}

	/**
	 * 自定义错误信息
	 *
	 * @param code
	 * @param msg
	 * @param <T>
	 * @return
	 */
	public static <T> JsonResult<T> error(Integer code, String msg) {
		JsonResult<T> result = new JsonResult<>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 请求失败，返回错误码及错误信息
	 *
	 * @param code 错误码
	 * @param msg  错误信息
	 * @return
	 */
	public static <T> JsonResult<T> error(Integer code, String msg, T data) {
		JsonResult<T> result = new JsonResult<T>();
		result.setCode(code);
		result.setData(data);
		result.setMsg(msg);
		return result;
	}

	public static JsonResult error(IResponseEnum responseEnum) {
		JsonResult result = new JsonResult<>();
		result.setCode(responseEnum.getCode());
		result.setMsg(responseEnum.getMessage());
		return result;
	}
}
