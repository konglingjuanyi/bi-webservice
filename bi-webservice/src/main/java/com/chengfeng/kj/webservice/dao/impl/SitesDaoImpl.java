package com.chengfeng.kj.webservice.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chengfeng.common.dao.BaseNeDaoImpl;
import com.chengfeng.kj.webservice.dao.ISitesDao;
import com.chengfeng.kj.webservice.domain.SiteVO;
import com.thinkjf.dao.DaoException;

@Repository("sitesDao")
public class SitesDaoImpl extends BaseNeDaoImpl implements ISitesDao {

	@Override
	public List<SiteVO> querySite(Map<String, Object> searchMap) throws DaoException {
		return super.queryForList("SiteVO.querySite",searchMap);
	}

	@Override
	public List<SiteVO> queryUpdateSite(Map<String, Object> searchMap)
			throws DaoException {
		return super.queryForList("SiteVO.queryUpdateSite",searchMap);
	}

	@Override
	public void doDeleteSiteBySend(Map<String, Object> searchMap)
			throws DaoException {
//		deleteRecord("SiteVO.deleteSiteBySend", searchMap);
	}

}
