package com.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParamUtil {
	private final static Logger logger = LoggerFactory.getLogger(ParamUtil.class);
	
	/**
	 * 将DTO里的属性值反射到MAP参数列表中
	 * 
	 * @param param
	 * @param dto
	 */
	public static Map<String, Object> putParam(Object dto) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			if (dto != null) {
				Field[] fields = dto.getClass().getDeclaredFields();
				if (fields != null) {
					for (Field f : fields) {
						String property = f.getName();
						if (property != null) {
							Method getMethod;
							try {
								// getMethod =
								// bean.getClass().getDeclaredMethod(property);
								PropertyDescriptor pdp = new PropertyDescriptor(property, dto.getClass());
								getMethod = pdp.getReadMethod();
								if (getMethod != null) {
									returnMap.put(property, getMethod.invoke(dto));
								}
							} catch (Exception e) {
								continue;
							}

						}
					}
				}

				Class<?> superClass = dto.getClass().getSuperclass();
				fields = superClass.getDeclaredFields();
				if (fields != null) {
					for (Field f : fields) {
						String property = f.getName();
						if (property != null) {
							Method getMethod;
							try {
								// getMethod =
								// bean.getClass().getDeclaredMethod(property);
								PropertyDescriptor pdp = new PropertyDescriptor(property, dto.getClass());
								getMethod = pdp.getReadMethod();
								if (getMethod != null) {
									returnMap.put(property, getMethod.invoke(dto));
								}
							} catch (Exception e) {
								continue;
							}

						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("DTO反射MAP参数列表出现异常...", e);
		}
		return returnMap;
	}

	public static int getTotalPages(int dataCount, int pageSize) {
		int totalPages = dataCount / pageSize;
		totalPages += dataCount % pageSize > 0 ? 1 : 0;
		return totalPages;
	}
}
