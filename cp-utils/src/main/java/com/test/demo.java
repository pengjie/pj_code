package com.test;
import java.util.Arrays;
import java.util.List;

public class demo {  
      
    public static void main(String[] args) {  
        /*String str = "12,60,-8,99,15,35,17,18,8,10,11,12";  */
    	String str = "12,11,8";  
        int sum = 35;  
        diguiSum(str,sum);  
    }  
    
    public static void diguiSum(String str,int sum) {  
        String[] x = str.split(",");  
        int[] array = arrayTransform(x);  
        for (int i = 0; i < array.length; i++) {  
            int[] cache = new int[i + 1];  
            int ceng = -1;  
            int cengQuit = i;  
            int startPiont = 0;  
            cir(ceng, cengQuit, startPiont, array, cache, sum);  
        }  
    }  
  
    // 递归求结果  
    public static void cir(int ceng, int cengQuit, int startPiont, int[] array, int[] cache, int sum) {  
        ceng++;  
        for (int i = startPiont; i < array.length; i++) {  
            cache[ceng] = array[i];  
            if (ceng == cengQuit) {  
                if (getSum(cache) == sum) {  
                    printcache(cache);  
                }  
                if (getSum(cache) > sum) {  
                    break;  
                }  
            }  
            if (ceng < cengQuit) {  
                startPiont = i + 1;  
                cir(ceng, cengQuit, startPiont, array, cache,sum);  
            }  
        }  
    }  
  
    // 获取组合数字之和  
    public static int getSum(int[] cache) {  
        int sum = 0;  
        for (int i = 0; i < cache.length; i++) {  
            sum = sum + cache[i];  
        }  
        return sum;  
    }  
  
    // 打印组合的可能  
    public static void printcache(int[] cache) {  
        for (int i = 0; i < cache.length; i++) {  
            System.out.print(cache[i] + ",");  
        }  
        System.out.println();  
    }  
  
    // 转换数组类型 且为提高效率做准备  
    public static int[] arrayTransform(String[] strArray) {  
        int length = 0;  
  
        int[] array = new int[strArray.length];  
        for (int i = 0; i < strArray.length; i++) {  
            array[i] = Integer.valueOf(strArray[i]);  
        }  
        Arrays.sort(array);  
        for (int i = 0; i < array.length; i++) {  
            if (array[i] > 35) {  
                length = i;  
                break;  
            }  
        }  
        int[] dest = new int[length];  
        System.arraycopy(array, 0, dest, 0, length);  
        return dest;  
    }  
}  