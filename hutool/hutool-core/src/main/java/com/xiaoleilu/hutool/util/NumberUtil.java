package com.xiaoleilu.hutool.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.xiaoleilu.hutool.exceptions.UtilException;

/**
 * 数字工具类<br>
 * 对于精确值计算应该使用 {@link BigDecimal}<br>
 * JDK7中<strong>BigDecimal(double val)</strong>构造方法的结果有一定的不可预知性，例如：
 * 
 * <pre>
 * new BigDecimal(0.1)
 * </pre>
 * 
 * 表示的不是<strong>0.1</strong>而是<strong>0.1000000000000000055511151231257827021181583404541015625</strong>
 * 
 * <p>
 * 这是因为0.1无法准确的表示为double。因此应该使用<strong>new BigDecimal(String)</strong>。
 * </p>
 * 相关介绍：
 * <ul>
 * <li>http://www.oschina.net/code/snippet_563112_25237</li>
 * <li>https://github.com/venusdrogon/feilong-core/wiki/one-jdk7-bug-thinking</li>
 * </ul>
 * 
 * @author Looly
 *
 */
public final class NumberUtil {

	/** 默认除法运算精度 */
	private static final int DEFAUT_DIV_SCALE = 10;

	private NumberUtil() {
	}

	/**
	 * 提供精确的加法运算
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算
	 * 
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算
	 * 
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况的时候,精确到小数点后10位,后面的四舍五入
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEFAUT_DIV_SCALE);
	}

	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度,后面的四舍五入
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 精确度，如果为负值，取绝对值
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		return div(v1, v2, scale, RoundingMode.HALF_UP);
	}
	
	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 精确度，如果为负值，取绝对值
	 * @param roundingMode 保留小数的模式 {@link RoundingMode}
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale, RoundingMode roundingMode) {
		if (scale < 0) {
			scale = -scale;
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, roundingMode).doubleValue();
	}

	/**
	 * 保留固定位数小数<br>
	 * 采用四舍五入策略 {@link RoundingMode#HALF_UP}
	 * 
	 * @param v 值
	 * @param scale 保留小数位数
	 * @return 新值
	 */
	public static double round(double v, int scale) {
		return round(v, scale, RoundingMode.HALF_UP);
	}

	/**
	 * 保留固定位数小数<br>
	 * 采用四舍五入策略 {@link RoundingMode#HALF_UP}
	 * 
	 * @param numberStr 数字值的字符串表现形式
	 * @param scale 保留小数位数
	 * @return 新值
	 */
	public static double round(String numberStr, int scale) {
		return round(numberStr, scale, RoundingMode.HALF_UP);
	}

	/**
	 * 保留固定位数小数
	 * 
	 * @param v 值
	 * @param scale 保留小数位数
	 * @param roundingMode 保留小数的模式 {@link RoundingMode}
	 * @return 新值
	 */
	public static double round(double v, int scale, RoundingMode roundingMode) {
		return round(Double.toString(v), scale, roundingMode);
	}

	/**
	 * 保留固定位数小数
	 * 
	 * @param numberStr 数字值的字符串表现形式
	 * @param scale 保留小数位数
	 * @param roundingMode 保留小数的模式 {@link RoundingMode}
	 * @return 新值
	 */
	public static double round(String numberStr, int scale, RoundingMode roundingMode) {
		final BigDecimal b = new BigDecimal(numberStr);
		return b.setScale(scale, roundingMode).doubleValue();
	}
	
	/**
	 * 保留小数位
	 * @param number 被保留小数的数字
	 * @param digit 保留的小数位数
	 * @return 保留小数后的字符串
	 */
	public static String roundStr(double number, int digit) {
		return String.format("%."+digit + 'f', number);
	}
	
	/**
	 * 格式化double
	 * 
	 * @param pattern 格式
	 * @param value 值
	 * @return 格式化后的值
	 */
	public static String decimalFormat(String pattern, double value) {
		return new DecimalFormat(pattern).format(value);
	}

	/**
	 * 格式化double，保留两位小数
	 * 
	 * @param value 值
	 * @return 结果
	 */
	public static String decimalFormat(double value) {
		return decimalFormat("0.00", value);
	}

	/**
	 * 格式化double，不保留小数
	 * 
	 * @param value 值
	 * @return 结果
	 */
	public static String decimalBlankFormat(double value) {
		return decimalFormat("0", value);
	}

	/**
	 * 是否为数字
	 * 
	 * @param str 字符串值
	 * @return 是否为数字
	 */
	public static boolean isNumber(String str) {
		if (StrUtil.isBlank(str)) {
			return false;
		}
		char[] chars = str.toCharArray();
		int sz = chars.length;
		boolean hasExp = false;
		boolean hasDecPoint = false;
		boolean allowSigns = false;
		boolean foundDigit = false;
		// deal with any possible sign up front
		int start = (chars[0] == '-') ? 1 : 0;
		if (sz > start + 1) {
			if (chars[start] == '0' && chars[start + 1] == 'x') {
				int i = start + 2;
				if (i == sz) {
					return false; // str == "0x"
				}
				// checking hex (it can't be anything else)
				for (; i < chars.length; i++) {
					if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
						return false;
					}
				}
				return true;
			}
		}
		sz--; // don't want to loop to the last char, check it afterwords
				// for type qualifiers
		int i = start;
		// loop to the next to last char or to the last char if we need another digit to
		// make a valid number (e.g. chars[0..5] = "1234E")
		while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				foundDigit = true;
				allowSigns = false;

			} else if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				hasDecPoint = true;
			} else if (chars[i] == 'e' || chars[i] == 'E') {
				// we've already taken care of hex.
				if (hasExp) {
					// two E's
					return false;
				}
				if (!foundDigit) {
					return false;
				}
				hasExp = true;
				allowSigns = true;
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (!allowSigns) {
					return false;
				}
				allowSigns = false;
				foundDigit = false; // we need a digit after the E
			} else {
				return false;
			}
			i++;
		}
		if (i < chars.length) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				// no type qualifier, OK
				return true;
			}
			if (chars[i] == 'e' || chars[i] == 'E') {
				// can't have an E at the last byte
				return false;
			}
			if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				// single trailing decimal point after non-exponent is ok
				return foundDigit;
			}
			if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
				return foundDigit;
			}
			if (chars[i] == 'l' || chars[i] == 'L') {
				// not allowing L with an exponent
				return foundDigit && !hasExp;
			}
			// last character is illegal
			return false;
		}
		// allowSigns is true iff the val ends in 'E'
		// found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
		return !allowSigns && foundDigit;
	}

	/**
	 * 生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
	 * 
	 * @param begin 最小数字（包含该数）
	 * @param end 最大数字（不包含该数）
	 * @param size 指定产生随机数的个数
	 */
	public int[] generateRandomNumber(int begin, int end, int size) {
		if (begin > end) {
			int temp = begin;
			begin = end;
			end = temp;
		}
		// 加入逻辑判断，确保begin<end并且size不能大于该表示范围
		if ((end - begin) < size) {
			throw new UtilException("Size is larger than range between begin and end!");
		}
		// 种子你可以随意生成，但不能重复
		int[] seed = new int[end - begin];

		for (int i = begin; i < end; i++) {
			seed[i - begin] = i;
		}
		int[] ranArr = new int[size];
		Random ran = new Random();
		// 数量你可以自己定义。
		for (int i = 0; i < size; i++) {
			// 得到一个位置
			int j = ran.nextInt(seed.length - i);
			// 得到那个位置的数值
			ranArr[i] = seed[j];
			// 将最后一个未用的数字放到这里
			seed[j] = seed[seed.length - 1 - i];
		}
		return ranArr;
	}

	/**
	 * 生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
	 * 
	 * @param begin 最小数字（包含该数）
	 * @param end 最大数字（不包含该数）
	 * @param size 指定产生随机数的个数
	 */
	public Integer[] generateBySet(int begin, int end, int size) {
		if (begin > end) {
			int temp = begin;
			begin = end;
			end = temp;
		}
		// 加入逻辑判断，确保begin<end并且size不能大于该表示范围
		if ((end - begin) < size) {
			throw new UtilException("Size is larger than range between begin and end!");
		}

		Random ran = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < size) {
			set.add(begin + ran.nextInt(end - begin));
		}

		Integer[] ranArr = set.toArray(new Integer[size]);
		return ranArr;
	}

	/**
	 * 判断String是否是整数
	 */
	public boolean isInteger(String s) {
		if ((s != null) && (s != ""))
			return s.matches("^[0-9]*$");
		else
			return false;
	}

	/**
	 * 判断字符串是否是浮点数
	 */
	public boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains(".")) return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 是否是质数
	 * 
	 * @param n 数字
	 * @return 是否是质数
	 */
	public static boolean isPrimes(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 阶乘：n!
	 * 
	 * @param n 值
	 * @return 阶乘
	 */
	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}

	/**
	 * 平方根算法<br>
	 * 推荐使用 {@link Math#sqrt(double)}
	 * 
	 * @param x 值
	 * @return 平方根
	 */
	public static long sqrt(long x) {
		long y = 0;
		long b = (~Long.MAX_VALUE) >>> 1;
		while (b > 0) {
			if (x >= y + b) {
				x -= y + b;
				y >>= 1;
				y += b;
			} else {
				y >>= 1;
			}
			b >>= 2;
		}
		return y;
	}

	/**
	 * 可以用于计算双色球、大乐透注数的方法 selectNum：<br>
	 * 选中了的小球个数 minNum：至少要选中多少个小球 <br>
	 * 比如大乐透35选5可以这样调用processMultiple(7,5); 就是数学中的：C75=7*6/2*1
	 */
	public int processMultiple(int selectNum, int minNum) {
		int result;
		result = mathSubnode(selectNum, minNum) / mathNode(selectNum - minNum);
		return result;
	}

	/**
	 * 最大公约数
	 * 
	 * @param m 第一个值
	 * @param n 第二个值
	 * @return 最大公约数
	 */
	public static int divisor(int m, int n) {
		while (m % n != 0) {
			int temp = m % n;
			m = n;
			n = temp;
		}
		return n;
	}

	/**
	 * 最小公倍数
	 * 
	 * @param m 第一个值
	 * @param n 第二个值
	 * @return 最小公倍数
	 */
	public static int multiple(int m, int n) {
		return m * n / divisor(m, n);
	}

	/**
	 * 给定范围内的整数列表，步进为1
	 * 
	 * @param start 开始（包含）
	 * @param stop 结束（包含）
	 * @return 整数列表
	 */
	public static int[] range(int start, int stop) {
		return range(start, stop, 1);
	}

	/**
	 * 给定范围内的整数列表
	 * 
	 * @param start 开始（包含）
	 * @param stop 结束（包含）
	 * @param step 步进
	 * @return 整数列表
	 */
	public static int[] range(int start, int stop, int step) {
		if (start < stop) {
			step = Math.abs(step);
		} else if (start > stop) {
			step = -Math.abs(step);
		} else {// start == end
			return new int[] { start };
		}

		int size = Math.abs((stop - start) / step) + 1;
		int[] values = new int[size];
		int index = 0;
		for (int i = start; (step > 0) ? i <= stop : i >= stop; i += step) {
			values[index] = i;
			index++;
		}
		return values;
	}

	/**
	 * 将给定范围内的整数添加到已有集合中，步进为1
	 * 
	 * @param start 开始（包含）
	 * @param stop 结束（包含）
	 * @param values 集合
	 * @return 集合
	 */
	public static Collection<Integer> appendRange(int start, int stop, Collection<Integer> values) {
		return appendRange(start, stop, 1, values);
	}

	/**
	 * 将给定范围内的整数添加到已有集合中
	 * 
	 * @param start 开始（包含）
	 * @param stop 结束（包含）
	 * @param step 步进
	 * @param values 集合
	 * @return 集合
	 */
	public static Collection<Integer> appendRange(int start, int stop, int step, Collection<Integer> values) {
		if (start < stop) {
			step = Math.abs(step);
		} else if (start > stop) {
			step = -Math.abs(step);
		} else {// start == end
			values.add(start);
			return values;
		}

		for (int i = start; (step > 0) ? i <= stop : i >= stop; i += step) {
			values.add(i);
		}
		return values;
	}

	/**
	 * 获得数字对应的二进制字符串
	 * 
	 * @param number 数字
	 * @return 二进制字符串
	 */
	public static String getBinaryStr(Number number) {
		if (number instanceof Long) {
			return Long.toBinaryString((Long) number);
		} else if (number instanceof Integer) {
			return Integer.toBinaryString((Integer) number);
		} else {
			return Long.toBinaryString(number.longValue());
		}
	}

	/**
	 * 二进制转int
	 * 
	 * @param binaryStr 二进制字符串
	 * @return int
	 */
	public int binaryToInt(String binaryStr) {
		return Integer.parseInt(binaryStr, 2);
	}

	/**
	 * 二进制转long
	 * 
	 * @param binaryStr 二进制字符串
	 * @return long
	 */
	public long binaryToLong(String binaryStr) {
		return Long.parseLong(binaryStr, 2);
	}

	/**
	 * 对比两个值得大小
	 * 
	 * @see Character#compare(char, char)
	 * 
	 * @param x 第一个值
	 * @param y 第二个值
	 * @return x==y返回0，x<y返回-1，x>y返回1
	 * @since 3.0.1
	 */
	public static int compare(char x, char y) {
		return x - y;
	}

	/**
	 * 对比两个值得大小
	 * 
	 * @see Double#compare(double, double)
	 * 
	 * @param x 第一个值
	 * @param y 第二个值
	 * @return x==y返回0，x<y返回-1，x>y返回1
	 * @since 3.0.1
	 */
	public static int compare(double x, double y) {
		return Double.compare(x, y);
	}

	/**
	 * 对比两个值得大小
	 * 
	 * @see Integer#compare(int, int)
	 * 
	 * @param x 第一个值
	 * @param y 第二个值
	 * @return x==y返回0，x<y返回-1，x>y返回1
	 * @since 3.0.1
	 */
	public static int compare(int x, int y) {
		if (x == y) {
			return 0;
		}
		if (x < y) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * 对比两个值得大小
	 * 
	 * @see Long#compare(long, long)
	 * 
	 * @param x 第一个值
	 * @param y 第二个值
	 * @return x==y返回0，x<y返回-1，x>y返回1
	 * @since 3.0.1
	 */
	public static int compare(long x, long y) {
		if (x == y) {
			return 0;
		}
		if (x < y) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * 对比两个值得大小
	 * 
	 * @see Short#compare(short, short)
	 * 
	 * @param x 第一个值
	 * @param y 第二个值
	 * @return x==y返回0，x<y返回-1，x>y返回1
	 * @since 3.0.1
	 */
	public static int compare(short x, short y) {
		if (x == y) {
			return 0;
		}
		if (x < y) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * 对比两个值得大小
	 * 
	 * @see Byte#compare(byte, byte)
	 * 
	 * @param x 第一个值
	 * @param y 第二个值
	 * @return x==y返回0，x<y返回-1，x>y返回1
	 * @since 3.0.1
	 */
	public static int compare(byte x, byte y) {
		return x - y;
	}
	
	/**
	 * 数字转字符串<br>
	 * 调用{@link Number#toString()}，并去除尾小数点儿后多余的0
	 *
	 * @param number A Number
	 * @return A String.
	 */
	public static String toStr(Number number) {
		if (number == null) {
			throw new NullPointerException("Number is null !");
		}
		
		if(false == ObjectUtil.isValidIfNumber(number)){
			throw new IllegalArgumentException("Number is non-finite!");
		}

		// 去掉小数点儿后多余的0
		String string = number.toString();
		if (string.indexOf('.') > 0 && string.indexOf('e') < 0 && string.indexOf('E') < 0) {
			while (string.endsWith("0")) {
				string = string.substring(0, string.length() - 1);
			}
			if (string.endsWith(".")) {
				string = string.substring(0, string.length() - 1);
			}
		}
		return string;
	}

	// --------------------------------------------------------------------- Private method start
	private int mathSubnode(int selectNum, int minNum) {
		if (selectNum == minNum) {
			return 1;
		} else {
			return selectNum * mathSubnode(selectNum - 1, minNum);
		}
	}

	private int mathNode(int selectNum) {
		if (selectNum == 0) {
			return 1;
		} else {
			return selectNum * mathNode(selectNum - 1);
		}
	}
	// --------------------------------------------------------------------- Private method end
}
