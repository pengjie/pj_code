/**
 * 
 */
package com.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author PENG
 * 比较重复数据
 */
public class CompData {
	
	
	static class TestBean{
		private String key ;
		
		public String getKey() {
			return key;
		}


		public void setKey(String key) {
			this.key = key;
		}

		public String getUUID() throws Exception{
			StringBuilder buffer = new StringBuilder() ;
			buffer.append(key == null ? "key": key);
			return MD5Util.MD5Encode(buffer.toString(), "UTF-8") ;
		}
	}
	/**
	 * as Map
	 * @param list
	 * @return
	 * @author Administrator
	 */
	public static Map<String,TestBean> createMap(List<TestBean> list)  {
		Map<String, TestBean> map = new HashMap<String, TestBean>();
		try {
			for(TestBean bean : list){
				map.put(bean.getUUID(), bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map ;
	}
	
   /**
	* 获取comMap中有的数据，且该数据在target中未存在
	* @param target，比较目标
	* @param comMap 需要比较的数据
	* @return comMap中比target多出的数据。
	*/	
	public static List<TestBean> getDiffrentList(Map<String, TestBean> target,
			   Map<String, TestBean> comMap) {
		List<TestBean> list = new ArrayList<TestBean>();
		for (Map.Entry<String, TestBean> entry : target.entrySet()) {
			String key = entry.getKey().trim();
			if (!comMap.containsKey(key)) {//如果没有则存放到List
				list.add(entry.getValue());
			}
		}
		return list;
	}
	
	/**
	* 获取comMap中有的数据，且该数据在target中未存在
	* @param target，比较目标
	* @param comMap 需要比较的数据
	* @return comMap中比target多出的数据。
	*/	
	public static List<TestBean> getSameList(Map<String, TestBean> target,
			   Map<String, TestBean> comMap) {
		List<TestBean> list = new ArrayList<TestBean>();
		for (Map.Entry<String, TestBean> entry : target.entrySet()) {
			String key = entry.getKey().trim();
			if (comMap.containsKey(key)) {
				TestBean valueDto = entry.getValue();
				//封装
				list.add(valueDto);
			}
		}
		return list;
	}
	
	/**
	 * 获取多出来的map
	 * @param list_1  源数据
	 * @param list_2 统计数据
	 * @return
	 */
	public static Map<String, List<TestBean>> getComResulMap(List<TestBean> list_1,
			List<TestBean> list_2){
		Map<String, List<TestBean>> map = new HashMap<String, List<TestBean>>();
		if(list_2 == null || list_2.size() <= 0){
			map.put("insertMarkets", list_1);
			return map;
		}
		// 1 转换成响应的map
		Map<String, TestBean> map_1 = createMap(list_1);
		Map<String, TestBean> map_2 = createMap(list_2);
		
		// 2 源数据多出统计的数据 -->新增
		List<TestBean> list_over_1 = getDiffrentList(map_1, map_2);
		// 3 源数据等于统计的数据 -->修改
		List<TestBean> list_over_2 = getSameList(map_1, map_2);
		
		map.put("insertMarkets", list_over_1);
		map.put("updateMarkets", list_over_2);
		return map ;
	}
	

}
