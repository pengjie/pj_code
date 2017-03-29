package com.inter.aop;

import java.io.Serializable;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

public class AnnotationMethodPointcutAdvisor extends AbstractGenericPointcutAdvisor {
	private String annotationName;
	private AnnotationMethodPointcut pointcut;
	private final Object pointcutMonitor = new SerializableMonitor();
	
	@Override
	public Pointcut getPointcut() {
		synchronized (this.pointcutMonitor) {
			if (this.pointcut == null) {
				this.pointcut = createPointcut();
				this.pointcut.setAnnotationName(this.annotationName);
			}
			return pointcut;
		}
	}

	protected AnnotationMethodPointcut createPointcut() {
		return new AnnotationMethodPointcut();
	}
	
	public void setAnnotationName(String annotationName) {
		this.annotationName = annotationName;
	}
	
	/**
	 * Empty class used for a serializable monitor object.
	 */
	private static class SerializableMonitor implements Serializable {
	}
}
