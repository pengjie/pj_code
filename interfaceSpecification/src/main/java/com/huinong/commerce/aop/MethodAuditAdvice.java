/**create by liuhua at 2016年11月8日 下午3:15:36**/
package com.huinong.commerce.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;

import com.huinong.commerce.aop.annotation.Audit;

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
