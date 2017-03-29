/**
 * 参数是否落在指定的区间上
 * "0<p<=10"  "11"
 */
package com.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peng
 *
 */
public class ParamRange {
	
	/*public static void main(String[] args) {
		String paramRangeString = "0<p<=10";
		String paramStr = "11";
		System.out.println(parseParamRange(paramRangeString, paramStr));
		
		paramRangeString = "0<=p<=10";
		paramStr = "5";
		System.out.println(parseParamRange(paramRangeString, paramStr));
	}*/
	
	/**
	 * 解析参数范围表达式
	 * 
	 * @param paramRangeString 表达式的字符串。表达式可能为(0<p<10 or 0<=p<10 or 0<p<=10 or 0<=p<=10)
	 * @param paramStr 需要判断的参数
	 * @return
	 */
	public  static boolean parseParamRange(String paramRangeString, String paramStr) {
		byte[] paramRangeByteArr = paramRangeString.getBytes(); // 将字符串转换为byte[]来处理
		List<Byte> minNumList = new ArrayList<Byte>();
		List<Byte> maxNumList = new ArrayList<Byte>();
		String leftOpt = "";
		String rightOpt = "";
		boolean isLeftOpt = true; // 标记是第一次出现还是第二次出现,第一次为true
		for (int i = 0, len = paramRangeByteArr.length; i < len; i++) {
			if (paramRangeByteArr[i] == 60) { // 60 是"<"对应的ascii 码
				if (paramRangeByteArr[i + 1] == 61) { // 61 是"="对应的ascii 码
					if (isLeftOpt) {
						leftOpt = "<=";
					}else{
						rightOpt = "<=";
					}
					i++;
				}else {
					if (isLeftOpt) {
						leftOpt = "<";
					}else{
						rightOpt = "<";
					}
				}
				isLeftOpt = false;
				continue;
			}
			if (48 <= paramRangeByteArr[i] && paramRangeByteArr[i] <= 57) { // 48是 "0"的 ascii码，57是"9"的ascii码
				if (isLeftOpt) {
					minNumList.add(paramRangeByteArr[i]);
				}else{
					maxNumList.add(paramRangeByteArr[i]);
				}
			}
		}
		
		// 将左边界的数从list中，放入byte[]，方便转换为String
		byte[] leftBorder = new byte[minNumList.size()];
		for (int i = 0, len = minNumList.size(); i < len; i++) {
			leftBorder[i] = minNumList.get(i);
		}
		
		// 将右边界的数从list中，放入byte[]，方便转换为String
		byte[] rightBorder = new byte[maxNumList.size()];
		for (int i = 0, len = maxNumList.size(); i < len; i++) {
			rightBorder[i] = maxNumList.get(i);
		}
		
		// 打印测试，看解析是否正确
		//System.out.println(new String(leftBorder) + leftOpt);
		//System.out.println(rightOpt + new String(rightBorder));
			
		return judgeParamRange(paramStr, new String(leftBorder), leftOpt, true)
				&& judgeParamRange(paramStr, new String(rightBorder), rightOpt, false);
	}
	
	
	/**
	 * 
	 * 判断参数是否在该范围
	 *
	 * @param paramStr 需要判断的参数
	 * @param borderParamStr 边界值
	 * @param opt 与边界值相关的符号(< or <=)
	 * @param isLeftBorder 是否是左边界
	 * @return
	 */
	private static boolean judgeParamRange(String paramStr, String borderParamStr, String opt, boolean isLeftBorder) {
		Float borderParam = Float.valueOf(borderParamStr); // 将边界值转换为 Float
		Float param = Float.valueOf(paramStr); // 将需要判断的参数转换为Float
		if (isLeftBorder) { // 如果为左边界
			if ("<".equals(opt)) {
				if (borderParam >= param) {
					return false;
				}
			} else if ("<=".equals(opt)) {
				if (borderParam > param) {
					return false;
				}
			}
		}else{// 否则为右边界
			if ("<".equals(opt)) {
				if (param >= borderParam) {
					return false;
				}
			}else if ("<=".equals(opt)) {
				if (param > borderParam) {
					return false;
				}
			}
		}
		return true;
	}

}
