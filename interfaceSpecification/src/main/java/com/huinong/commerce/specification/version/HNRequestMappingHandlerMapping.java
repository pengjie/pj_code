/**create by liuhua at 2016年7月14日 下午5:23:13**/
package com.huinong.commerce.specification.version;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class HNRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

	@Override
	protected RequestCondition<InterfaceVesrsionCondition> getCustomTypeCondition(Class<?> handlerType) {
		InterfaceVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, InterfaceVersion.class);
		return createCondition(apiVersion);

	}

	@Override
	protected RequestCondition<InterfaceVesrsionCondition> getCustomMethodCondition(Method method) {
		InterfaceVersion apiVersion = AnnotationUtils.findAnnotation(method, InterfaceVersion.class);
		return createCondition(apiVersion);

	}

	private RequestCondition<InterfaceVesrsionCondition> createCondition(InterfaceVersion apiVersion) {
		return apiVersion == null ? null : new InterfaceVesrsionCondition(apiVersion.value());
	}

}