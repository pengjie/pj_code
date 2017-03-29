package com.inter.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class AnnotationMethodPointcut extends StaticMethodMatcherPointcut {
	private String annotationName;
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean matches(Method method, Class<?> targetClass) {
		try {
			Class<? extends Annotation> clazz = (Class<? extends Annotation>) Class.forName(annotationName);
			Object object = method.getAnnotation(clazz);
			if (null != object) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setAnnotationName(String annotationName) {
		this.annotationName = annotationName;
	}

}
