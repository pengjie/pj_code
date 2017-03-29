package com.test;
import java.util.Arrays;  
import java.util.HashMap;  
import java.util.Map;  
  
/** 
 * @author admin 
 * 
 */  
public class NSum  
{  
    private static int[] array={1,1,2,3,1,2,2,2,2,1,1,1,1};   
      
    public static boolean isNSumNum(){  
        int sum =0 ;  
        int k = array.length;  
        for(int i = 0;i<k;i++){  
            sum+=array[i];  
        }  
        //如果数组的总和不是偶数，这个数组肯定不满足条件  
        if((sum&1)==1){  
            return false;  
        }  
        int middle = sum>>1;  
        Arrays.sort(array);  
        int larget = array[k-1];  
          
        //最大值肯定在一组中，如果最大值比均值大，这个数组肯定不满足条件  
        if(larget>middle){  
            return false;  
        }  
        if(larget==middle){  
            return true;  
        }  
          
        int newTarget = middle-larget;  
          
        for(int i =0;i<array.length&&array[i]<=newTarget;i++){  
            if(array[i]==newTarget){  
                return true;  
            }  
        }  
          
        return threeSum(newTarget,-1);  
    }  
      
    public static boolean threeSum(int target,int z){  
        for(int i = z+1 ;i<array.length&&array[i]<=target;i++){  
            if(twoSum1(target-array[i],i)){  
                return true;  
            }else{  
                return threeSum(target-array[i],i);  
            }  
        }  
        return false;  
    }  
      
    /** 
     * 两个数相加的目标位target 
     * @param target 
     */  
    public static boolean twoSum1(int target,int z){  
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();  
        for(int i =z+1;i<array.length&&array[i]<=target;i++){  
            if(!map.containsKey(array[i])){  
                map.put(target-array[i], i);  
            }  
            if(map.containsKey(array[i])){  
                int k = map.get(array[i]);  
                if(i!=k&&z!=i&&k!=z){  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
      
    public static void main(String[] args)  
    {  
        System.out.println(isNSumNum());  
    }  
}  
