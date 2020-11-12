package com.mhyl.performance.appraisal.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author ArchieDing
 * @since 2020/09/08
 */
public class PwdUtils {
	public static String createSalt() {
		return IdUtil.simpleUUID();
	}

	public static String encodePassword(String pwd, String salt) {
		return DigestUtil.md5Hex(pwd + salt);
	}

	public static boolean checkPassword(String checkPwd, String inputPwd, String salt) {
		String pwd = DigestUtil.md5Hex(inputPwd + salt);
		return pwd.equals(checkPwd);
	}
}
