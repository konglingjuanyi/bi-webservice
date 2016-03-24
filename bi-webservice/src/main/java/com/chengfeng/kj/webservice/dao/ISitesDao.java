package com.chengfeng.kj.webservice.dao;

import java.util.List;
import java.util.Map;

import com.chengfeng.kj.webservice.domain.SiteVO;
import com.thinkjf.dao.DaoException;

/**
 * 
 * 功能描述：快捷网点信息操作DAO
 *
 * Copyright: Copyright (c) 2013
 *
 * Company: 丞风智能科技有限公司
 * 
 * @author Lei.Zhao
 *
 * @version 2015-8-26
 */
public interface ISitesDao {

	/**
	 * 获取网点信息
	 * @param searchMap
	 * @return
	 * @throws DaoException
	 */
	public List<SiteVO> querySite(Map<String, Object> searchMap) throws DaoException;
	
	/**
	 * 获取更新网点信息
	 * @param searchMap
	 * @return
	 * @throws DaoException
	 */
	public List<SiteVO> queryUpdateSite(Map<String, Object> searchMap) throws DaoException;
	
	/**
	 * 删除网点信息
	 * @param searchMap
	 * @throws DaoException
	 */
	public void doDeleteSiteBySend(Map<String, Object> searchMap) throws DaoException;
}
