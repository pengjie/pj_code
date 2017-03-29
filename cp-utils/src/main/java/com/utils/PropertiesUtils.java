package com.utils;

import java.util.Properties;

public class PropertiesUtils
{
	private static final Properties props = new Properties();

	/*static {
		try {
			props.load(Splider.class.getClassLoader().getResourceAsStream(
					"splider.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			assert false;
		}
	}*/

	/**
	 * 获取配置文件值
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertiesUrl(String key)
	{
		return props.getProperty(key);
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
		String url = props.getProperty(key);
		if (null != obj && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				url = url.replace("{" + i + "}", String.valueOf(obj[i]));
			}
		}
		return url;
	}
}
