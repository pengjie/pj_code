package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*long xx = 1488509747 ;
		Date date = new Date(xx);
		System.out.println(date);*/
		
		test();
	}
	
	public static void test() {
		int amt = 10 ;
		int total = 0 ;
		List<Integer> dataList = new ArrayList<Integer>();
		Map map = new HashMap(); 
		int[] array = { 1, 1, 2, 3, 1, 2, 2, 2, 2, 1, 1, 1, 1 };
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			total = total + array[i];
			if(total >= amt){
				total = array[i] ;
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				map.put(uuid, dataList);
				dataList = new ArrayList<Integer>();
				dataList.add(array[i]);
			}else{
				dataList.add(array[i]);
			}
			if(i == array.length - 1){
				if(total < amt){
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					map.put(uuid, dataList);
				}
			}
		}
		System.out.println(new Gson().toJson(map));
	}

}
