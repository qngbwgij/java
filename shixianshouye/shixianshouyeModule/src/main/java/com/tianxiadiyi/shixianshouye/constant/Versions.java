package com.tianxiadiyi.shixianshouye.constant;

import com.tianxiadiyi.shixianshouye.commercial.Enterprise;

public class Versions {
	/**
	 * 当前版本
	 */
	public static final String VERSION = "10.0.0";

	/**
	 * 当前版本
	 * 
	 * @return
	 */
	public static String getVersion() {
		return Enterprise.isEp() ? VERSION + "-enterprise" : VERSION;
	}

}
