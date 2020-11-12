package com.mhyl.performance.appraisal.utils;

import cn.hutool.json.JSONUtil;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ArchieDing
 * @since 2020/09/02
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
	/**
	 * map转对象
	 *
	 * @param map
	 * @param beanClass
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
		if (map == null) {
			return null;
		}

		T obj = beanClass.newInstance();

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}

			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}

		return obj;
	}

	/**
	 * 对象转map
	 *
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>(0);

		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}

		return map;
	}

	/**
	 * 根据属性名获取属性值
	 *
	 * @param fieldName
	 * @param object
	 * @return
	 */
	public static double getFieldValue(String fieldName, Object object) {
		try {

			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return (double) field.get(object);
		} catch (Exception e) {

			return 0;
		}
	}

	/**
	 * 通过json数据格式转换实现复制list
	 *
	 * @param list
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> copyList(List<?> list, Class<T> clazz) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<>();
		}
		return JsonUtils.toList(JsonUtils.toJson(list), clazz);
	}

	/**
	 * 通过json数据格式转换实现复制Map
	 *
	 * @param map
	 * @return
	 */
	public static Map<String, Object> copyMap(Map map) {
		return JSONUtil.parseObj(JSONUtil.toJsonStr(map));
	}
}
