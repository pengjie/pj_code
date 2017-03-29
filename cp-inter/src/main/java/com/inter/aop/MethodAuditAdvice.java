package com.inter.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;

import com.inter.aop.annotation.Audit;

@Service("methodAuditAdvice")
public class MethodAuditAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = invocation.proceed();
		Audit audit = invocation.getMethod().getAnnotation(Audit.class);
		if (null != audit) {
			String d = audit.data();
			System.out.println(d + "......111111111111111");
		}
		return result;
	}

}
