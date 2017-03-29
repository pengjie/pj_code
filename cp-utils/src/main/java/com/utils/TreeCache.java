package com.utils;

import java.util.List;

public class TreeCache {
	
	private static Long time = null; 
	
	private static final int expireTime = 1000*60*60*24 ;
	
	private static List<Object> groupMap;
	
	public static List<Object> getGroupCategories(){
		if(groupMap != null &&  groupMap.size() > 0){
			if(isExpire()){
				return null ;
			}
			return groupMap;
		}
		return null ;
	}

	
	public synchronized static void push(List<Object> gMap){
		 groupMap = gMap;
		 time = System.currentTimeMillis();
	}
	
	private static boolean isExpire(){
		if(time == null) return true ;
		return System.currentTimeMillis() - time > expireTime;
	}
	
	
}
