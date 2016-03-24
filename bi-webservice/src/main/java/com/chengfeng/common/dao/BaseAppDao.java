/**
 * BaseAppDao.java 
 * 2014-1-12 上午1:07:44
 *  www.thinkjf.com
 * CopyRight 2013-2013 ThinkJF, Inc. All rights reserved.
 */
package com.chengfeng.common.dao;

import java.util.Date;

import com.thinkjf.dao.sqlmapping.BaseDao;

/**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ThinkJF</p>
 * @author Freeman
 * @version 1.0 2014-1-12 上午1:07:44
 */
public interface BaseAppDao extends BaseDao {
	/**
	 * 获取当前时间
	 * @return
	 */
	Date getCurrentTime();
}
