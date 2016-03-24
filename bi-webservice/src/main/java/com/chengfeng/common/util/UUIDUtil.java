package com.chengfeng.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 
 * 功能描述：UUID 帮助类
 *
 * Copyright: Copyright (c) 2013
 *
 * Company: 丞风智能科技有限公司
 * 
 * @author Lei.Zhao
 *
 * @version 2015-8-3下午2:00:21
 */
public class UUIDUtil {
    /** 
 	* 产生一个32位的GUID 
 	* @return 
 	*/ 
	public static String newGUID() { 
		UUID uuid = UUID.randomUUID(); 
		return uuid.toString().replace("-", ""); 
	} 


	/** 
   * 获取32位GUID 
   * 
   * @return 
   */ 
  public static String getId() { 
      try { 
          MessageDigest md = MessageDigest.getInstance("MD5"); 
          UUID uuid = UUID.randomUUID(); 
          String guidStr = uuid.toString(); 
          md.update(guidStr.getBytes(), 0, guidStr.length()); 
          return new BigInteger(1, md.digest()).toString(16); 
      } catch (NoSuchAlgorithmException e) { 
          return null; 
      } 
  }
}
