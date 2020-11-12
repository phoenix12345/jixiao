package com.mhyl.performance.appraisal.http;

import com.mhyl.performance.appraisal.exception.IResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
@ApiModel(value = "响应结构")
public class JsonResult<T> {
	/**
	 * 状态码
	 */
	@ApiModelProperty(value = "返回状态码；200:成功")
	private Integer code;
	/**
	 * 消息
	 */
	@ApiModelProperty(value = "描述信息")
	private String msg;

	/**
	 * 服务器时间戳
	 */
	@ApiModelProperty(value = "服务器时间")
	private Long timestamp = System.currentTimeMillis();

	/**
	 * 参数信息
	 */
	private T data;

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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@ApiModelProperty(value = "成功标识；true：成功；false:失败")
	public boolean isSuccess() {
		return this.code == 200;
	}
}
