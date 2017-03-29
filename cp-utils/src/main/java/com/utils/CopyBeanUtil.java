package com.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * @author sq
 *
 * 2016年5月6日
 */
public  class CopyBeanUtil {

	private CopyBeanUtil() {
	    }

    private final static class helper {
        private static CopyBeanUtil util;

        private static CopyBeanUtil fetchPojo() {
            if (util == null) {
                util = new CopyBeanUtil();
            }
            return util;
        }
    }

    
    public static CopyBeanUtil getInstance(){
    	return helper.fetchPojo();
    }
	
	/**
     * 复制相同属性名称的值并且值得类型要一致
     * @param fromObj
     * @param toObj
     * @throws Exception 
     */
    public void copyBeanProperties(Object fromObj,Object toObj) throws Exception{
    	if(toObj == null || fromObj == null) return ;
    	Field[] toFields = toObj.getClass().getDeclaredFields();
    	if(toFields == null || toFields.length == 0) return ;
    	for(Field field : toFields ){
    		String toFiledName = field.getName();
    		Object value = getValueFromFields(fromObj, toFiledName);
    		if(value != null) {
    			field.setAccessible(true);
    			field.set(toObj, value);
    		}
    	}
    }
    
    
    private Object getValueFromFields(Object fromObj,String fieldName){
    	try {
			if(fieldName == null || "".equals(fieldName) || fromObj == null) return null ;
			PropertyDescriptor pdp = new PropertyDescriptor(fieldName, fromObj.getClass());
			Method method = pdp.getReadMethod();
			if(method != null) return method.invoke(fromObj);
			return null ;
		} catch (Exception e) {
			return null ;
		}
    }
}
