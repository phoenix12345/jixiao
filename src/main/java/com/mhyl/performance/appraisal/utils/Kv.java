package com.mhyl.performance.appraisal.utils;

import java.util.HashMap;

/**
 * @author ArchieDing
 * @since 2020/09/03
 */
public class Kv extends HashMap {
	public Kv() {
	}

	/**
	 * 初始化键值对
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static Kv by(Object key, Object value) {
		return new Kv().set(key, value);
	}

	/**
	 * 追加键值对
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Kv set(Object key, Object value) {
		super.put(key, value);
		return this;
	}
}
