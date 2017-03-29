/**create by liuhua at 2016年7月7日 下午2:54:44**/
package com.huinong.commerce.specification.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huinong.commerce.specification.result.PageInfo;
import com.huinong.commerce.specification.result.ResultMessage;

/**
 * 
 * @author liuhua
 *
 */
public class MessageUtils {
	public static Gson gson  = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static Gson gsonAll  = new GsonBuilder().serializeNulls().create();
	
	public static String toJson(ResultMessage resultMessage){
		Map<String, Object> map = removeNull(resultMessage);
		return gsonAll.toJson(map);
	}
	
	public static String toJsonWithoutNull(ResultMessage resultMessage){
		Map<String, Object> map = removeNull(resultMessage);
		return gson.toJson(map);
	}
	
	private static Map<String, Object> removeNull(ResultMessage resultMessage){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (null != resultMessage) {
			String status = resultMessage.getStatus();
			if ("SUCCESS".equals(status)) {
				map.put("status", resultMessage.getStatus());
				map.put("message", resultMessage.getMessage());
				Object data = resultMessage.getData();
				if (null != data) {
					map.put("data", data);
				}
				PageInfo pageInfo = resultMessage.getPageInfo();
				if (null != pageInfo) {
					map.put("pageInfo", pageInfo);	
				}
			}else{
				map.put("status", resultMessage.getStatus());
				map.put("message", resultMessage.getMessage());
				map.put("errorCode", resultMessage.getErrorCode());
				map.put("errorMessage", resultMessage.getErrorMessage());
			}
		}
		return map;
	}
}
