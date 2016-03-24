package com.chengfeng.common.dao;

import java.util.Date;

import com.thinkjf.dao.sqlmapping.BaseSqlMapClientDao;

/**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ThinkJF</p>
 * @author Freeman
 * @version 1.0 2014-1-11 下午3:46:36
 */
public abstract class BaseAppDaoImpl extends BaseSqlMapClientDao implements BaseAppDao {
	
    public static final String GET_CURRENT_TIME = "ThinkJF.getCurrentDBTime";
	
	/**
	 * 获取数据库当前时间
	 * @return
	 */
	public Date getCurrentTime(){
		return (Date) getSqlMapClientTemplate().queryForObject(GET_CURRENT_TIME);
	}

}
