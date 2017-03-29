package com.test;

public class alg1 {  
    public static void main(String argv[]) {  
        int[] array1 = {10,2,7,4,5,6,3,8,9,1};  
        int[] array2 = {1,2,3,4,5,6,7,8,9,10};  
        int[] array3 = {1,2,3,4,5,6,7,8,9,10};  
        alg1.execute1(array1, 8);  
        System.out.println();
        alg1.execute2(array2, 8); 
        System.out.println();
        alg1.execute3(array3, 8);  
    }  
      
    //思路：使用hash表存储数组各元素是否存在的标志，然后遍历数组，判断sum与当前数组元素的差值是否在hash表中,  
    //若为真则打印，该算法不要求数组有序，但要求一个hash数组的额外空间，时间复杂度是O(n)  
    private static void execute1(int[] array, int m) {  
        int size = array.length;  
        int hash[] = new int[size];  
        for(int i = 0; i < size; i++) {  
            hash[array[i]%size] = 1;  
        }  
          
        for(int i = 0; i < size; i++) {  
            int tmp = m - array[i];  
            if((tmp > array[i]) && (hash[tmp%size] == 1)){  
                System.out.println(array[i] + " " + tmp);  
            }  
        }  
    }  
      
    //思路：该方法的前提是要求数组是有序的，然后再遍历数组，判断sum与数组元素的差值是否在数组中，由于数组有序所以可以采用二分查找的方法  
    //二分查找的时间复杂度为O(logn),排序的时间复杂度是O(nlogn),查找n次，总的时间复杂度为O(nlogn)，避免了空间的浪费  
    private static void execute2(int[] array, int m) {  
        for(int i = 0; i < array.length; i++) {  
            int tmp = m - array[i];  
            if (tmp > array[i]) {  
                if (binarySearch(array, tmp) != -1) {  
                    System.out.println(array[i] + " " + tmp);  
                }  
            }  
        }  
    }  
    private static int binarySearch(int[] array, int key) {  
        if (array.length == 0) {  
            return -1;  
        }  
          
        int first = 0;  
        int last = array.length -1;  
          
        int mid;  
        while(first <= last) {  
            mid = (first + last) / 2;  
            if (array[mid] == key) {  
                return mid;  
            } else if (array[mid] < key) {  
                first = mid + 1;  
            } else {  
                last = mid -1;  
            }  
        }  
        return -1;  
    }  
      
    //思路：该方法的前提是要求数组是有序的，使用两个指针，分别指向最后一个元素和第一个元素，判断它们的和是否等于sum，若等于则打印，并且first向前移动，last也向前移动  
    //若它们的和小于sum，则说明first太小了，需要first向前移动，若它们的和大于sum，则说明last太大了，需要last向前移动，直到last>=first  
    private static void execute3(int[] array, int m) {  
        int first = 0;  
        int last = array.length -1;  
        int sum = 0;  
        while(first < last ) {  
            sum = array[first] + array[last];  
            if (sum == m) {  
                System.out.println(array[first] + " " + array[last]);  
                first++;  
                last--;  
            } else if (sum < m) {  
                first++;  
            } else {  
                last--;  
            }  
        }  
    }  
      
}  
