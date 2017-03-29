package com.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class ConvertUtil {
	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
	}

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {/*
																	if (obj == null) {
																	return null;
																	}
																	Map<String, Object> map = new HashMap<String, Object>();
																	try {
																	BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
																	PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
																	for (PropertyDescriptor property : propertyDescriptors) {
																	String key = property.getName();
																	
																	// 过滤class属性
																	if (!key.equals("class")) {
																	// 得到property对应的getter方法
																	Method getter = property.getReadMethod();
																	Object value = getter.invoke(obj);
																	
																	map.put(key, value);
																	}
																	}
																	} catch (Exception e) {
																	System.out.println("transBean2Map Error " + e);
																	}
																	return map;
																	*/
		return simpleBean2Map(obj);
	}

	public static Map<String, Object> simpleBean2Map(Object obj) {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			if (obj == null)
				return null;
			Class<? extends Object> clazz = obj.getClass().getSuperclass();
			Field[] fds = null;
			if (clazz != null) {
				fds = clazz.getDeclaredFields();
				if (fds != null && fds.length > 0) {
					for (Field f : fds) {
						f.setAccessible(true);
						String name = f.getName();
						ret.put(name, f.get(obj));
					}
				}
			}
			fds = obj.getClass().getDeclaredFields();
			if (fds != null && fds.length > 0) {
				for (Field f : fds) {
					f.setAccessible(true);
					String name = f.getName();
					ret.put(name, f.get(obj));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 简单的bean2Map  仅限父类只有一级、属性无复合属性
	 * @param obj
	 * @return
	 *//*
		public static Map<String, Object> simpleBean2Map(Object bean) {
		return simpleBean2Map(bean, true);
		}*/

	/**
	 * 简单的bean2Map  仅限父类只有一级、属性无复合属性
	 * @param obj
	 * @return
	 */
	/*public static Map<String, Object> simpleBean2Map(Object bean, boolean containNullValue) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (bean != null) {
			Field[] fields = bean.getClass().getDeclaredFields();
			String property;
			if (fields != null) {
				for (Field f : fields) {
					property = f.getName();
					if (property == null)
						continue;
					try {
						PropertyDescriptor pdp = new PropertyDescriptor(property, bean.getClass());
						Method getMethod = pdp.getReadMethod();
						if (getMethod != null) {
							Object value = getMethod.invoke(bean, new Object[0]);
							if (value != null) {
								returnMap.put(property, value);
							} else {
								if (containNullValue)
									returnMap.put(property, value);
							}
						}
					} catch (Exception localException) {
					}
				}
			}
	
			Class<? extends Object> superClass = bean.getClass().getSuperclass();
			fields = superClass.getDeclaredFields();
			if (fields != null) {
				for (Field f : fields) {
					property = f.getName();
					if (property == null)
						continue;
					try {
						PropertyDescriptor pdp = new PropertyDescriptor(property, bean.getClass());
						Method getMethod = pdp.getReadMethod();
						if (getMethod != null) {
							Object value = getMethod.invoke(bean, new Object[0]);
							if (value != null) {
								returnMap.put(property, value);
							} else {
								if (containNullValue)
									returnMap.put(property, value);
							}
						}
					} catch (Exception localException1) {
					}
				}
			}
		}
		return returnMap;
	}*/
}