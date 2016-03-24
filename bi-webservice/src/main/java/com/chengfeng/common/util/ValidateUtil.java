package com.chengfeng.common.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 验证帮助类
 * 
 * @author Jeanie
 * 
 */
public class ValidateUtil {

	/**
	 * 手机号码验证
	 * 
	 * @author Jeanie
	 * @param str
	 * @return
	 */
	public static boolean isHandset(String str) {
		String regex = "^[1]+[3,4,5,8]+\\d{9}$";
		return match(regex, str) || isHKMobile(str) || isTWMobile(str);
	}

	/**
	 * 多个联系电话验证
	 * 
	 * @author Laughing
	 * @param linkPhone
	 * @return
	 */
	public static boolean isLinkPhone(String linkPhone) {
		if (linkPhone != null && linkPhone.trim().length() > 0) {
			String phone = linkPhone.substring(0, linkPhone.length());
			String phone2 = linkPhone.substring(linkPhone.length() - 1,
					linkPhone.length());

			String[] phones = phone.split(";");
			if (phone.indexOf(";;") != -1 || phone2.equals(";")) {
				return false;
			}
			for (int i = 0; i < phones.length; i++) {
				String s = phones[i];
				if (s.length() < 7) {
					return false;
				}
				if (!isHandset(s) && !isCurrentTelephone(s)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 传真验证
	 * 
	 * @author Laughing
	 * @param str
	 * @return
	 */
	public static boolean isFax(String str) {
		return isCurrentTelephone(str);
	}

	/**
	 * 邮箱验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		String regex = "^[0-9,a-z,A-Z,\\!\\#\\*\\&\\%\\.\\_]+@[0-9,a-z,A-Z]+\\.[0-9,a-z,A-Z]+$";
		return match(regex, str);
	}

	/**
	 * 多个无区号座机号、有区号座机号验证
	 * 
	 * @author Jeanie
	 * @param str
	 * @return
	 */
	public static boolean isTelephone(String str) {
		boolean flag = true;
		String[] phones = str.split(";");
		for (String phone : phones) {
			if (!isCurrentTelephone(phone)) {
				return false;
			}
		}
		return flag;
	}

	/**
	 * 无区号座机号、有区号座机号验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCurrentTelephone(String str) {
		boolean flag = true;
		String regex = "^((\\d{3,5})|\\d{3,5}-)?\\d{7,10}$";
		String regex1 = "^(\\d{3,5})?\\d{7,10}$";
		if (!match(regex, str) && !match(regex1, str)
				&& !isForeignMobileNO(str)) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 字符串长度验证不能超过strSize*2个字符或strSize个汉字
	 * 
	 * @author Jeanie
	 * @param str
	 * @param strSize
	 * @return
	 */
	public static boolean vaildateStringLength(String str, Integer strSize) {
		str = str.trim();
		if (str.length() == 0 || str.equals(""))
			return false;
		int i = 0;
		char c = 0;
		int length = 0;
		for (; i < str.length(); i++) {
			c = str.charAt(i);
			if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
				length += 1;
			} else if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
				length += 2;
			} else {
				length += 1;
			}
		}
		if (length <= strSize * 2)
			return true;
		return false;
	}

	public static Integer getStringLength(String str) {
		int len = str.length();
		str = str.replaceAll("\\p{InCJK Unified Ideographs}", "");
		str = str.replaceAll("\\p{InCJK Symbols and Punctuation}", "");
		str = str.replaceAll("\\p{InHALFWIDTH_AND_FULLWIDTH_FORMS}", "");
		int afterlen = str.length();
		int chineselength = (len - afterlen) * 2;
		return afterlen + chineselength;
	}

	/**
	 * 调用正则表达式
	 * 
	 * @author Jeanie
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * @desc:验证是否是double类型
	 * @author Clyde
	 * @date:2014-11-5
	 * @param str
	 * @return
	 */
	public static boolean isDoubleValue(String str){
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		boolean flag = match("^(\\d*)(.\\d*)$", str);
		if (!flag) {
			return false;
		}
		try {
			Double.valueOf(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 邮政编码验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPostCode(String str) {
		String regex = "\\d{6}(?!\\d)";// 需求改动可以0开头;2014-08-22
		return match(regex, str);
	}

	/**
	 * 判断字符串是否都是是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		return match("^[0-9]+$", str);
	}

	/**
	 * 判断是否为编号格式
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isCode(String code) {
		return match("^[0-9\\.]+$", code);
	}

	/**
	 * 判断某字符串是否都是英文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChar(String str) {
		return match("^[a-zA-Z]+$", str);
	}

	/**
	 * 判断字符是否是英文字符或数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCharOrNum(String str) {
		return match("^[a-zA-Z0-9]+$", str);
	}

	/**
	 * 判断是否是 英文字符、数字或者点
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCharNumOrPoint(String str) {
		return match("^[a-zA-Z0-9\\.]+$", str);
	}

	/**
	 * 判断字符串是否是英文字母+数字的组合
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCharNum(String str) {
		String regEx = "^(([A-Za-z]+[0-9]+)|([0-9]+[A-Za-z]+))[A-Za-z0-9]*$";
		return match(regEx, str);
	}

	/**
	 * TODO:验证是否包含汉字
	 * 
	 * @param str
	 * @return Date: 2014-7-3下午12:04:16
	 */
	public static boolean isContainChineseChar(String str) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		for (int i = 0; i < str.length(); i++) {
			if (p.matches(regEx, str.substring(i, i + 1))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @方法描述:非空验证
	 * @author Clyde
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法描述:验证是否存在不合法字符
	 * @author Laughing
	 * @param str
	 * @return boolean
	 */
	public static boolean isIllegal(String str) {
		String validateString = str.replace("(", "");
		String resultString = validateString.replace(")", "");
		String result = resultString.replace("-", "");
		return match("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]*$", result);
	}

	public static boolean isQuoteNameIllegal(String str) {
		return match("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w\\-]*$", str);
	}

	/**
	 * 去除
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		if(str==null){
			return "";
		}
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		String result = m.replaceAll("");
		return result;
	}

	/**
	 * @desc:自定义比较两个变量是否相等
	 * @author Clyde
	 * @date:2014-3-13
	 * @param arg0
	 * @param arg1
	 * @return true为相等
	 */
	public static boolean compareVariable(Object arg0, Object arg1) {
		if (arg0 == arg1) {
			return true;
		}
		String str1 = "";
		String str2 = "";
		if (arg0 == null && arg1 != null) {
			str2 = arg1.toString();
		} else if (arg0 != null && arg1 == null) {
			str1 = arg0.toString();
		} else if (arg0 != null && arg1 != null) {
			str1 = arg0.toString();
			str2 = arg1.toString();
		}
		if (str1.equals(str2)) {
			return true;
		}
		return false;
	}

	/**
	 * @desc:验证double类型的上限true为通过(验证两位小数)
	 * @author Clyde
	 * @date:2014-4-21
	 * @param digit
	 * @param places
	 *            共多少位(小数加整数位)
	 * @return
	 */
	public static boolean doValidMaxDouble(Double digit, int places) {
		return doValidMaxDouble(digit, places-2, 2);
	}
	
	/**
	 * 验证double类型的上限true为通过
	 * @time 2014-12-12上午11:10:23
	 * @param digit(要验证的数)
	 * @param number(整数位)
	 * @param decimals(小数位)
	 * @return
	 */
	public static boolean doValidMaxDouble(Double digit, int number,int decimals) {
		if (digit == null) {
			return false;
		}
		double temp = Math.pow(10, number);
		double result = temp - Math.pow(0.1,decimals);
		return Math.abs(digit) <= result;
	}

	/**
	 * @desc:验证int类型的上限true为通过
	 * @author Clyde
	 * @date:2014-5-20
	 * @param digit
	 *            要比较的数字
	 * @param places
	 *            共多少位
	 * @return
	 */
	public static boolean doValidMaxIntValue(Integer digit, int places) {
		if (digit == null) {
			return false;
		}
		double result=Math.pow(10, places) - 1;
		return digit<0?(-digit <= result):(digit <= result);
	}
	
	/**
	 * @desc:验证单号是否有重复的可能
	 * @author Clyde
	 * @date:2014-5-10
	 * @param oldStart
	 * @param oldEnd
	 * @param nowStart
	 * @param nowEnd
	 * @return
	 */
	public static boolean doValidEachEwbNo(String oldStart, String oldEnd,
			String nowStart, String nowEnd) {
		boolean flag = true;
		int result_1 = nowStart.compareTo(oldStart);
		int result_2 = nowStart.compareTo(oldEnd);

		int result_3 = nowEnd.compareTo(oldStart);
		int result_4 = nowEnd.compareTo(oldEnd);
		if (oldStart.length() == nowStart.length()) {
			if (result_1 >= 0 && result_2 <= 0) {
				flag = false;
			} else if (result_3 >= 0 && result_4 <= 0) {
				flag = false;
			} else if (result_1 < 0 && result_2 < 0 && result_3 > 0
					&& result_4 > 0) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 值长度验证
	 * 
	 * @param size
	 * @param value
	 * @return
	 */
	public static boolean doValidatorLength(int size, String value) {
		boolean pass = true;
		if (value.length() >= size) {
			pass = false;
		}
		return pass;
	}

	public static Double setFormatDouble(final String value, final int integer,
			final int decimal) {
		Double result = 0D;
		if (value == null || value.length() <= 0) {
			return result;
		}
		BigDecimal bg = new BigDecimal(value);
		String[] vals = bg.toPlainString().split("\\.");
		if (vals.length == 1) {
			if (vals[0].length() > integer) {
				result = Double.parseDouble(vals[0].substring(0, integer)
						+ ".00");
			} else {
				result = Double.parseDouble(vals[0] + ".00");
			}
		} else {
			if (vals[0].length() > integer) {
				if (vals[1].length() > decimal) {
					result = Double.parseDouble(vals[0].substring(0, integer)
							+ "." + vals[1].substring(0, decimal));
				} else {
					result = Double
							.parseDouble(vals[0].substring(0, integer)
									+ "."
									+ StringUtils.rightPad(vals[1], decimal,
											"0"));
				}
			} else {
				if (vals[1].length() > decimal) {
					result = Double.parseDouble(vals[0] + "."
							+ vals[1].substring(0, decimal));
				} else {
					result = Double.parseDouble(vals[0] + "."
							+ StringUtils.rightPad(vals[1], decimal, "0"));
				}
			}
		}
		return result;
	}


	/**
	 * @desc:验证香港手机号
	 * @author Clyde
	 * @date:2014-8-22
	 * @param source
	 * @return
	 */
	public static boolean isHKMobile(String source) {
		return match(
				"(^[569]\\d{7}$)|(^852[569]\\d{7}$)|(^00852[569]\\d{7}$)|(^852-[569]\\d{7}$)|(^00852-[569]\\d{7}$)",
				source);
	}

	/**
	 * @desc:验证台湾手机号
	 * @author Clyde
	 * @date:2014-8-22
	 * @param source
	 * @return
	 */
	public static boolean isTWMobile(String source) {
		return match(
				"(9\\d{8}$)|(8869\\d{8}$)|(008869\\d{8}$)|(886-9\\d{8}$)|(00886-9\\d{8}$)",
				source);
	}

	/**
	 * 国外手机号码验证
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isForeignMobileNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^[\\d+]+$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * @desc:验证电话号码（包括台湾、香港）
	 * @author Clyde
	 * @date:2014-8-22
	 * @param str
	 * @return
	 */
	public static boolean isTel(String str) {
		boolean flag = true;
		String regex = "^((\\d{3,5})|\\d{3,5}-)?\\d{7,10}$";
		String regex1 = "^(\\d{3,5})?\\d{7,10}$";
		if (!match(regex, str) && !match(regex1, str)
				&& !isForeignMobileNO(str)&&!isLaxTel(str)) {
			flag = false;
		}
		return flag;
	}

	/**
	 * @desc:内地手机号验证
	 * @author Clyde
	 * @date:2014-9-25
	 * @param str
	 * @return
	 */
	public static boolean isInnerMobile(String str) {
		String regex = "^[1]+[3,4,5,8]+\\d{9}$";
		return match(regex, str);
	}
	
	/**
	 * @desc:验证不严格的手机号
	 * @author Clyde
	 * @date:2014-9-25
	 * @param str
	 * @return
	 */
	public static boolean isLaxMobile(String str){
		String regex = "^[\\d-+()]{5,20}$";
		return match(regex, str);	
	}
	
	/**
	 * @desc:验证不严格的电话
	 * @author Clyde
	 * @date:2014-9-25
	 * @param str
	 * @return
	 */
	public static boolean isLaxTel(String str){
		String regex = "^[\\d-+()]{5,20}$";
		return match(regex, str);	
	}
	
	/**
	 * @desc:验证手机号（传志修改为只校验内地手机号码--罗涛）
	 * @author Clyde
	 * @date:2014-8-22
	 * @param str
	 * @return
	 */
	public static boolean isChinaPhone(String str) {
		if (isInnerMobile(str)) {
			return true;
		}
		return false;
	}

	/**
	 * @desc:验证多个电话
	 * @author Clyde
	 * @date:2014-8-22
	 * @param strPhone
	 * @return
	 */
	public static boolean isMultiPhone(String strPhone) {
		if (!isEmpty(strPhone)) {
			if (strPhone.indexOf(";;") != -1) {
				return false;
			}
			String[] phones = strPhone.split(";");
			for (int i = 0; i < phones.length; i++) {
				String s = phones[i];
				if (s.length() > 18) {
					return false;
				}
				if (!match("^[\\d-]*$", s)) {
					return false;
				}
				if (s.indexOf("-") == 0 || s.lastIndexOf("-") == s.length() - 1) {
					return false;
				}
			}
		}
		return true;
	}

}