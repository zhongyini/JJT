package com.xxx.wechat.common.test;

import org.junit.Test;

import com.xxx.wechat.common.utils.CheckUtils;

public class CommonTest {
	@Test
	public void testCommon(){
		System.out.println(CheckUtils.isNullOrEmpty(""));
	}
}
