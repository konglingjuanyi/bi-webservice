/**
 * 
 */
package com.chengfeng.common.util;

import java.util.logging.Level;

import org.apache.log4j.Logger;

/**
 * 功能描述: 日志写入类
 * Copyright: Copyright (c) 2013
 * Company: 
 * @author zwczhangwencong@163.com
 * @version 1.0 2013-12-3
 * @see
 * HISTORY
 * 2013-12-3创建文件
 */
public class LogHelper {
	
	 private static Logger logger = Logger.getLogger(LogHelper.class.getName());
	 
 /**
	 * 把消息写入日志中
	 * @param level为日志等级
	 * @param classname类名称
	 *  @param method方法名
	 *  @param content日志内容
	 */
	 public static void PrintLog(Loggerlevel level, String classname, String method, String content)
	 {		 
		 logger = Logger.getLogger(classname);
		 
		 if(Loggerlevel.FATAL == level)
		 {
			 logger.fatal(method+" - "+content);
		 }
		 
		 if(Loggerlevel.ERROR == level)
		 {
			 logger.error(method+" - "+content);
		 }
		 
		 if(Loggerlevel.WARN == level)
		 {
			 logger.warn(method+" - "+content);
		 }
		 
		 if(Loggerlevel.INFO == level)
		 {
			 logger.info(method+" - "+content);
		 }
	 }
	 
	 public static void PrintLog(Loggerlevel level, String classname, String method, Exception e)
	 {		 
		 logger = Logger.getLogger(classname);
		 
		 if(Loggerlevel.FATAL == level)
		 {
			 logger.fatal(method+" - "+e);
		 }
		 
		 if(Loggerlevel.ERROR == level)
		 {
			 logger.error(method+" - "+e);
		 }
		 
		 if(Loggerlevel.WARN == level)
		 {
			 logger.warn(method+" - "+e);
		 }
		 
		 if(Loggerlevel.INFO == level)
		 {
			 logger.info(method+" - "+e);
		 }
	 }
	 
}