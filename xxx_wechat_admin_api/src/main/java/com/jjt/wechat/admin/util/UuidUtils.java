package com.xxx.wechat.admin.util;

import java.util.UUID;

public class UuidUtils {

	public static String get32UUId() {

		String str = UUID.randomUUID().toString();

		return str.replace("-", "");
	}

	public static String get16UUId() {
		int machineId = 1;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		return machineId + String.format("%015d", hashCodeV);
	}
}
