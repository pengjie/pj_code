package com.utils;

import java.util.*;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
public class SortMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("bad","12");
        map.put("adb","15");
        map.put("dba","15");
        System.out.println(map.toString());
        SortMap xx = new SortMap();
        Map<String, String> map1 = xx.mapSortByKey(map);
        System.out.println(map1.toString());
    }

    /**
     * 根据key排序
     * @param oriMap
     * @return
     */
    /**
     * Map按key值重新排序
     * @return
     */
    private static SortedMap<String, String> mapSortByKey(Map<String, String> unSortMap) {
        TreeMap<String, String> result = new TreeMap<String, String>();
        Object[] unSortKey = unSortMap.keySet().toArray();
        Arrays.sort(unSortKey);
        for (int i = 0; i < unSortKey.length; i++) {
            result.put(unSortKey[i].toString(), unSortMap.get(unSortKey[i]));
        }
        return result.tailMap(result.firstKey());
    }

}
