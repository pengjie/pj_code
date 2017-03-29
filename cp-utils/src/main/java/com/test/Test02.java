package com.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.utils.CollectionTools;

public class Test02 {
	
	public static void main(String[] args) {
		
		List<Direct> validList = new ArrayList<Direct>();
		Direct e1 = new Direct() ;
		e1.setAccno("9906");
		e1.setType("0");
		e1.setUserid("0001");
		e1.setAmt(11D);
		validList.add(e1);
		
		Direct e2 = new Direct() ;
		e2.setAccno("9906");
		e2.setType("0");
		e2.setUserid("0001");
		e2.setAmt(3D);
		validList.add(e2);
		
		Direct e6 = new Direct() ;
		e6.setAccno("9906");
		e6.setType("0");
		e6.setUserid("0001");
		e6.setAmt(6D);
		validList.add(e6);
		
		Direct e7 = new Direct() ;
		e7.setAccno("9906");
		e7.setType("0");
		e7.setUserid("0001");
		e7.setAmt(3D);
		validList.add(e7);
		
		Direct e3 = new Direct() ;
		e3.setAccno("9905");
		e3.setType("0");
		e3.setUserid("0001");
		e3.setAmt(12D);
		validList.add(e3);
		
		Direct e4 = new Direct() ;
		e4.setAccno("9907");
		e4.setType("0");
		e4.setUserid("0001");
		e4.setAmt(9D);
		validList.add(e4);
		
		//分组成多个list
		Map<String, List> listMap;
		try {
			listMap = CollectionTools.classifyVal(validList, "accno");
			//将各子list 按累计金额拆分多组子list
			for(String key : listMap.keySet()){
				splitAmt(listMap.get(key),20d);
				System.out.println();
				//System.out.println(key);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
				
	}
	
	static class  ValComparator implements Comparator<Direct>{
		public int compare(Direct o1, Direct o2) {  
			int seq1 = 0 ;
			int seq2 = 0 ;
			seq1 = o1.getAmt().intValue() ;
			seq2 = o2.getAmt().intValue() ;
			return seq1 - seq2;  
		}
	}
	
	public static Map<String,List<Direct>> splitAmt(List<Direct> dataList,Double amt) {
		//排序
		Collections.sort(dataList, new ValComparator());
		
		Double total = 0d ;
		String uuid = "" ;
		//拆分金額
		List<Direct> resultList = new ArrayList<Direct>();
		Map<String,List<Direct>> map = new HashMap<String,List<Direct>>();
		for(int i=0 ; i<dataList.size() ; i++){
			Direct direct = dataList.get(i);
			total = total + direct.getAmt();
			//当前金额小于限额金额
			if(total < amt){
				resultList.add(direct);
			}else{
				//当前金额大于或等于限额金额
				uuid = UUID.randomUUID().toString().replaceAll("-", "");
				map.put(uuid, resultList);
				//将resultList 置空
				total = direct.getAmt() ;
				resultList = new ArrayList<Direct>();
				resultList.add(direct);
			}
			if(i == dataList.size() - 1){
				uuid = UUID.randomUUID().toString().replaceAll("-", "");
				map.put(uuid, resultList);
			}
		}
		System.out.println(new Gson().toJson(map));
		return map ;
		/*double totalAmt = 0.0d ;
		for(String key : map.keySet()){
			totalAmt = 0.0d ;
			List<Direct> lst = map.get(key) ;
			for(Direct direct : lst){
				totalAmt = totalAmt + direct.getAmt(); 
			}
		}*/
	}
	
	
	
}
