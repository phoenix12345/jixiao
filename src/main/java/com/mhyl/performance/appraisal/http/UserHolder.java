package com.mhyl.performance.appraisal.http;

import cn.hutool.core.convert.Convert;
import com.mhyl.performance.appraisal.domain.entity.SystemUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ArchieDing
 * @since 2020/09/02
 */
public class UserHolder {
	private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<>();

	public static void set(String key, Object value) {
		Map<String, String> map = getLocalMap();
		map.put(key, value == null ? "" : value.toString());
	}

	public static <T> T get(String key, Class<T> type) {
		Map<String, String> map = getLocalMap();
		return Convert.convert(type, map.get(key));
	}

	public static <T> T get(String key, Class<T> type, Object def) {
		Map<String, String> map = getLocalMap();
		return Convert.convert(type, map.getOrDefault(key, String.valueOf(def == null ? "" : def)));
	}

	public static String get(String key) {
		Map<String, String> map = getLocalMap();
		return map.getOrDefault(key, "");
	}

	public static Map<String, String> getLocalMap() {
		Map<String, String> map = THREAD_LOCAL.get();
		if (map == null) {
			map = new HashMap<>(10);
			THREAD_LOCAL.set(map);
		}
		return map;
	}

	public static void setLocalMap(Map<String, String> threadLocalMap) {
		THREAD_LOCAL.set(threadLocalMap);
	}

	public static void setSystemUser(SystemUser systemUser) {
		Map<String, String> map = getLocalMap();
		map.put(TokenConstant.KEY_USER_ID, String.valueOf(systemUser.getId()));
		map.put(TokenConstant.KEY_USER_NAME, systemUser.getName());
		map.put(TokenConstant.KEY_ACCOUNT, systemUser.getAccount());
	}

	/**
	 * 账号id
	 *
	 * @return
	 */
	public static String getUserId() {
		return get(TokenConstant.KEY_USER_ID, String.class, 0L);
	}

	/**
	 * 账号id
	 *
	 * @param userId
	 */
	public static void setUserId(Long userId) {
		set(TokenConstant.KEY_USER_ID, userId);
	}

	public static void setUserId(String userId) {
		set(TokenConstant.KEY_USER_ID, userId);
	}

	public static String getUserIdStr() {
		return String.valueOf(getUserId());
	}

	/**
	 * 账号表中的account
	 *
	 * @return
	 */
	public static String getAccount() {
		return get(TokenConstant.KEY_ACCOUNT, String.class);
	}

	/**
	 * 账号表中的account
	 *
	 * @param account
	 */
	public static void setAccount(String account) {
		set(TokenConstant.KEY_ACCOUNT, account);
	}


	/**
	 * 账号表中的user_name
	 *
	 * @return
	 */
	public static String getName() {
		return get(TokenConstant.KEY_USER_NAME, String.class);
	}

	/**
	 * 账号表中的user_name
	 *
	 * @param userName
	 */
	public static void setName(String userName) {
		set(TokenConstant.KEY_USER_NAME, userName);
	}

	/**
	 * 获取用户token
	 *
	 * @return
	 */
	public static String getToken() {
		return get(TokenConstant.TOKEN_HEADER_KEY, String.class);
	}

	public static void setToken(String token) {
		set(TokenConstant.TOKEN_HEADER_KEY, token);
	}

	public static void remove() {
		if (THREAD_LOCAL != null) {
			THREAD_LOCAL.remove();
		}
	}
}
