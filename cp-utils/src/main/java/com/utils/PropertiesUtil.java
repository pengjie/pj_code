package com.utils;

import java.util.Properties;

/**
 * 类说明 属性配置加载
 **/
public class PropertiesUtil
{

	private static Properties properties;

	/**
	 * 获取配置信息
	 * 
	 */
	public static String getProperty(String key)
	{

		return properties.getProperty(key);
	}

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		PropertiesUtil.properties = properties;
	}

	/**
	 * 替换
	 * 
	 * @param url
	 * @param obj
	 * @return
	 */
	public static String formatRequestUrl(String key, Object... obj)
	{
		String url = properties.getProperty(key);
		if (null != obj && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				url = url.replace("{" + i + "}", String.valueOf(obj[i]));
			}
		}
		return url;
	}

}
