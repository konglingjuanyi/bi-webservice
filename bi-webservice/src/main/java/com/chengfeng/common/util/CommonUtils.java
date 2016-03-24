package com.chengfeng.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * 功能描述：公用帮助类
 * </p>
 * <p>
 * Company: 丞风智能科技有限公司
 * </p>
 * 
 * @author marry
 * @version 1.0 2014-4-12下午10:04:19
 */
public class CommonUtils {

	static Log log = LogFactory.getLog(CommonUtils.class);

	/**
	 * 格式化金额
	 * 
	 * @param amount
	 * @return
	 */
	public static String formatAmount(Double amount) {
		if (amount == null) {
			return "";
		}
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat
				.getInstance();
		decimalFormat.applyPattern("0.00");
		return decimalFormat.format(amount);
	}

	/**
	 * 格式化double
	 * 
	 * @param arg
	 * @return
	 */
	public static Double formatDouble(Double arg) {
		try {
			return Double.parseDouble(formatAmount(arg));
		} catch (Exception e) {
			log.error("[Error]", e);
			return 0.0;
		}
	}

	/**
	 * 格式化String to String
	 * 
	 * @param arg
	 * @return
	 */
	public static String formatStringToString(String arg) {
		try {
			return formatAmount(Double.parseDouble(arg));
		} catch (Exception e) {
			log.error("[Error]", e);
			return "0.00";
		}
	}

	/**
	 * @desc:按格式，格式化double类型的值，返回double
	 * @author Clyde
	 * @date:2014-7-12
	 * @param arg0
	 * @param places
	 *            格式
	 * @return
	 */
	public static Double formatDouble(Double arg0, String places) {
		try {
			return Double.parseDouble(formatDoubleToString(arg0, places));
		} catch (Exception e) {
			log.error("[Error]", e);
			return 0.0;
		}
	}

	/**
	 * @desc:按格式，格式化double类型值 返回string
	 * @author Clyde
	 * @date:2014-7-12
	 * @param arg0
	 * @param format
	 *            格式
	 * @return
	 */
	public static String formatDoubleToString(Double arg0, String format) {
		if (arg0 == null) {
			return "";
		}
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat
				.getInstance();
		decimalFormat.applyPattern(format);
		return decimalFormat.format(arg0);
	}

	public static String formatDoubleToString(Double number) {
		if (number == null)
			return "0.00";
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat
				.getInstance();
		decimalFormat.applyPattern("0.00");
		return decimalFormat.format(number);
	}

	public static String formatDoubleToStringTo4(Double number) {
		if (number == null)
			return "0.0000";
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat
				.getInstance();
		decimalFormat.applyPattern("0.0000");
		return decimalFormat.format(number);
	}

	public static String subString(String str, int bytes) {
		int count = 0;
		String reStr = "";
		if (str == null) {
			return "";
		}
		char[] tempChar = str.toCharArray();
		for (int i = 0; i < tempChar.length; i++) {
			String s1 = str.valueOf(tempChar[i]);
			byte[] b = s1.getBytes();
			count += b.length;
			if (count <= bytes) {
				reStr += tempChar[i];
			}
		}
		return reStr;
	}

	/**
	 * Java 比较两个 List 的值是否相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
		if (a.size() != b.size())
			return false;
		Collections.sort(a);
		Collections.sort(b);
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).equals(b.get(i)))
				return false;
		}
		return true;
	}

	/**
	 * 将array中的内容以delimiter为间隔拼接字符串
	 * 
	 * @param array
	 * @param delimiter
	 * @return
	 */
	public static String join(Object[] array, String delimiter) {
		if (array == null) {
			throw new IllegalArgumentException();
		}

		if (array.length == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (Object item : array) {
			builder.append(item.toString() + delimiter);
		}
		builder.delete(builder.length() - delimiter.length(), builder.length());
		return builder.toString();
	}

	/**
	 * 将list中的内容以delimiter为间隔拼接字符串
	 * 
	 * @param list
	 * @param delimiter
	 * @return
	 */
	public static String join(List<?> list, String delimiter) {
		if (list == null) {
			throw new IllegalArgumentException();
		}
		return join(list.toArray(), delimiter);
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Item1");
		list.add("Item2");
		list.add("Item3");
		String expected = "Item1;Item2;Item3";
		System.out.println(expected.equals(join(list, ";")));
	}

	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat year = new SimpleDateFormat("yyyy");

	/**
	 * 获取格式化日期
	 * 
	 * @return
	 */
	public static String getDateFormated(Date date) {
		if (date == null) {
			return "";
		} else {
			return dateFormat.format(date);
		}
	}

	/**
	 * 获取格式化时间
	 * 
	 * @return
	 */
	public static String getTimeFormated(Date date) {
		if (date == null) {
			return "";
		} else {
			if (year.format(date).equals("1980")) {
				return "";
			}
			return timeFormat.format(date);
		}
	}

	/**
	 * TODO:转账金额大小写转换(目前仅寄件运单)
	 * 
	 * @author Ailsa Date: 2014-3-27下午8:00:16
	 */
	public static String onChangAmount(Double money) {
		if (money == null) {
			return "";
		}
		try {
			char[] s1 = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
			char[] s4 = { '分', '角', '元', '拾', '佰', '仟', '万', '拾', '佰', '仟',
					'亿', '拾', '佰', '仟', '万' };
			String str = String.valueOf(Math.round(money * 100 + 0.00001));
			String result = "";
			for (int i = 0; i < str.length(); i++) {
				int n = str.charAt(str.length() - 1 - i) - '0';
				result = s1[n] + "" + s4[i] + result;
			}
			result = result.replaceAll("零仟", "零");
			result = result.replaceAll("零佰", "零");
			result = result.replaceAll("零拾", "零");
			result = result.replaceAll("零亿", "亿");
			result = result.replaceAll("零万", "万");
			result = result.replaceAll("零元", "元");
			result = result.replaceAll("零角", "零");
			result = result.replaceAll("零分", "零");

			result = result.replaceAll("零零", "零");
			result = result.replaceAll("零亿", "亿");
			result = result.replaceAll("零零", "零");
			result = result.replaceAll("零万", "万");
			result = result.replaceAll("零零", "零");
			result = result.replaceAll("零元", "元");
			result = result.replaceAll("亿万", "亿");

			result = result.replaceAll("零$", "");
			result = result.replaceAll("元$", "元整");
			result = result.replaceAll("角$", "角整");
			return result;
		} catch (Exception e) {
			log.error("[Error:金额转换出错！]", e);
			return "";
		}
	}
}
