package com.jjt.wechat.core.config;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAspect {
	private static Logger logger = LoggerFactory.getLogger(DaoAspect.class);

	@Pointcut("execution(* com.qiaohu.wechat.core.dao.*.*(..)) ")
	public void daoInterceptor() {
	}

	@Before("daoInterceptor())")
	public void doBefore(JoinPoint joinPoint) {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		@SuppressWarnings("rawtypes")
		Class targetClass = null;
		try {
			if (methodName.contains("update")) {
				DataSourceContextHolder.write();
//				logger.info("method:" + methodName + "数据库读写状态:"
//						+ DataSourceContextHolder.getJdbcType());
				return;
			}
			if (methodName.contains("select")) {
				DataSourceContextHolder.read();
//				logger.info("method:" + methodName + "数据库读写状态:"
//						+ DataSourceContextHolder.getJdbcType());
				return;
			}
			boolean isRead = false;
			targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					logger.info("methodName:" + methodName);
					@SuppressWarnings("rawtypes")
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						TargetDataSourceType ds = method
								.getAnnotation(TargetDataSourceType.class);
//						logger.info(methodName + ";isRead:" + ds);
						if (ds != null && ds.isRead()) {
//							logger.info("isRead:" + isRead);
							isRead = true;
							break;
						}

					}
				}
			}
			if (isRead) {
				DataSourceContextHolder.read();
			} else {
				DataSourceContextHolder.write();
			}
			// logger.info("method:" + methodName + "数据库读写状态:"
			// + DataSourceContextHolder.getJdbcType());

		} catch (ClassNotFoundException e) {

		}

	}

	@After("@annotation(ds)")
	public void restoreDataSource(JoinPoint point, TargetDataSourceType ds) {
		logger.info("Revert DataSource : {} > {}", ds.isRead(),
				point.getSignature());
		DataSourceContextHolder.write();
	}

}
