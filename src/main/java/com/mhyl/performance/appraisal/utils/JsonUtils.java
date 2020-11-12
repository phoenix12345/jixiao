package com.mhyl.performance.appraisal.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author ArchieDing
 * @since 2020/09/03
 */
public class JsonUtils {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		// Bean中为NULL的不输出
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// Bean中不存在的字段不校验
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		OBJECT_MAPPER.setDateFormat(sdf);
	}

	/**
	 * 将 Java 对象转为 JSON 字符串
	 */
	public static String toJson(Object obj) {
		String jsonStr;
		try {
			jsonStr = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jsonStr;
	}

	/**
	 * 将 JSON 字符串转为 Java 对象
	 */
	public static <T> T toObject(String json, Class<T> type) {
		T obj;
		try {
			obj = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return obj;
	}

	/**
	 * 将 JSON 字符串转为 Java 对象
	 */
	public static Object toObject(String json) {
		if (json.startsWith("{") && json.endsWith("}")) {
			return toObject(json, Map.class);
		}
		if (json.startsWith("[{") && json.endsWith("}]")) {
			return toList(json, Map.class);
		}
		if (json.startsWith("[\"") && json.endsWith("\"]")) {
			return toList(json, String.class);
		}
		if (json.startsWith("[") && json.endsWith("]")) {
			return toList(json, Number.class);
		}
		if (json.startsWith("\"") && json.endsWith("\"")) {
			return toObject(json, String.class);
		}
		return toObject(json, Number.class);
	}

	public static <T> List<T> toList(String json, Class<T> clzz) {
		List<T> list;
		try {
			JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, clzz);
			list = OBJECT_MAPPER.readValue(json, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public static <T> Map<String, T> toMap(String json, Class<T> clzz) {
		Map<String, T> map;
		try {
			JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, String.class, clzz);
			map = OBJECT_MAPPER.readValue(json, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return map;
	}
}
