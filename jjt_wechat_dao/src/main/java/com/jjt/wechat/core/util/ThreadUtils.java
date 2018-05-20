package com.jjt.wechat.core.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadUtils {
	public static final ExecutorService FIXEDTHREADPOOL = Executors.newFixedThreadPool(5);
}
