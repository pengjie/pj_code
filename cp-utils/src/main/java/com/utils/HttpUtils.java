package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

/**
 * http connection
 * 
 * @author sq
 * 
 */
public class HttpUtils
{
	private static Gson gson = new Gson();

	/**
	 * post请求
	 * 
	 * @param url
	 *            url链接
	 * @param params
	 *            参数集合
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String url, Map<String, Object> params)
			throws Exception
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL postUrl = new URL(url);
			URLConnection connection = postUrl.openConnection();
			/* 设置一般请求属性 */
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			/* URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true * */
			connection.setDoOutput(true);
			connection.setDoInput(true);
			/** 用PrintWriter包装 返回写入到此连接的输出流 */
			out = new PrintWriter(connection.getOutputStream());
			/* 组装成参数串 */
			String param = parseParams(params);
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e2) {
				throw e2;
			}
		}
		return result;
	}

	/**
	 * 把map变成url参数串 例如：key=value&year=2014
	 * 
	 * @param map
	 * @return
	 */
	private static String parseParams(Map<String, Object> map)
	{
		StringBuffer sb = new StringBuffer();
		if (map != null) {
			for (Entry<String, Object> e : map.entrySet()) {
				sb.append(e.getKey()).append("=").append(e.getValue())
						.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 抛送数据给惠农网分析系统
	 * 
	 * @param list
	 * @return
	 */
	public static String sendPostHWN(List<?> list)
	{
		try {
			if (null == list || list.size() == 0) {
				return null;
			}
			// 把数据抛给分析系统
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("data", gson.toJson(list));
			return HttpUtils
					.sendPost(
							PropertiesUtils
									.formatRequestUrl(URLDescription.HNW_POST_DATA_URL),
							params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
