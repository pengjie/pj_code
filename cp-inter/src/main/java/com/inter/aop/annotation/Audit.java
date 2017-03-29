package com.inter.aop.annotation;

import com.inter.aop.queue.Constants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Audit {
	String hnUserId();
	String hnUserAccount();
	boolean isNewRecord();
	String data ();
	Constants.AuditBusinessType businessType ();
}
