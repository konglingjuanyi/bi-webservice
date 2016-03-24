package com.chengfeng.kj.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.chengfeng.kj.webservice.dao.ISitesDao;
import com.chengfeng.kj.webservice.domain.SiteVO;
import com.chengfeng.kj.webservice.service.ISitesService;
import com.thinkjf.service.ServiceException;

@Service("sitesService")
public class SitesServiceImpl implements ISitesService {

	@Resource(name ="sitesDao")
    private ISitesDao sitesDao;
	
	@Override
	public List<SiteVO> querySite(Map<String, Object> searchMap)
			throws ServiceException {
		return sitesDao.querySite(searchMap);
	}

	@Override
	public List<SiteVO> queryUpdateSite(Map<String, Object> searchMap)
			throws ServiceException {
		// TODO Auto-generated method stub
		return sitesDao.queryUpdateSite(searchMap);
	}

	@Override
	public void doDeleteSiteBySend(Map<String, Object> searchMap)
			throws ServiceException {
		sitesDao.doDeleteSiteBySend(searchMap);
	}

	@Override
	public List<String> getBusinessParams(Map<String, Object> searchMap)
			throws ServiceException {
		List<SiteVO> list = sitesDao.queryUpdateSite(searchMap);
		if(list.size()>0){
			List<String> jsonlist = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SITE_CODE", list.get(i).getSiteCode());
				jsonObj.put("SITE_NAME", list.get(i).getSiteName());
				jsonObj.put("PARENT_SITE_CODE", list.get(i).getSuperiorSite()==null?"":list.get(i).getSuperiorSite());
				jsonObj.put("SCAN_CENTER_SITE_CODE", list.get(i).getSuperiorFinanceCenter()==null?"":list.get(i).getSuperiorFinanceCenter());
				jsonObj.put("BILL_CENTER_SITE_CODE", list.get(i).getSuperiorFinanceCenter()==null?"":list.get(i).getSuperiorFinanceCenter());
				String centersitesign = list.get(i).getCentersitesign()==null?"":list.get(i).getCentersitesign();
				if(centersitesign.equals("1") || list.get(i).getType().equals("转运中心")){
					jsonObj.put("SITE_TYPE_NAME", "一级分拨中心");
				}else{
					if(list.get(i).getType().equals("中心")){
						jsonObj.put("SITE_TYPE_NAME", "一级加盟网点");
						jsonObj.put("FIRST_CENTER_SITE_CODE", list.get(i).getSuperiorSite()==null?"/":list.get(i).getSuperiorSite());
					}else{
						String superCentersitesign = list.get(i).getSuperCentersitesign()==null?"":list.get(i).getSuperCentersitesign();
						if(superCentersitesign.equals("1")){
							jsonObj.put("SITE_TYPE_NAME", "一级加盟网点");
							jsonObj.put("FIRST_CENTER_SITE_CODE", list.get(i).getSuperiorSite()==null?"/":list.get(i).getSuperiorSite());
						}else{
							jsonObj.put("SITE_TYPE_NAME", "二级加盟网点");
							jsonObj.put("FIRST_CENTER_SITE_CODE", list.get(i).getFirstCenterSiteCode()==null?"/":list.get(i).getFirstCenterSiteCode());
						}
					}
				}
				jsonObj.put("COD_LIMIT_AMOUNT", 0.0);
				jsonObj.put("POD_LIMIT_AMOUNT", 0.0);
				jsonObj.put("SITE_STATUS", list.get(i).getBlNotInput()==null?0:list.get(i).getBlNotInput());
				jsonObj.put("MONEY_TYPE_NAME", "/");
				jsonObj.put("COUNTRY_NAME", "中国");
				jsonObj.put("PROVINCE_NAME", list.get(i).getProvince()==null?"/":list.get(i).getProvince());
				jsonObj.put("CITY_NAME", list.get(i).getCity()==null?"/":list.get(i).getCity());
				jsonObj.put("COUNTY_NAME", list.get(i).getRangeName()==null?"/":list.get(i).getRangeName());
				jsonObj.put("ADDRESS", "/");
				jsonObj.put("AREA_NAME", list.get(i).getAreaName()==null?"/":list.get(i).getAreaName());
				jsonObj.put("SITE_SERVICES_TYPE", 3);
				jsonObj.put("RD_STATUS", 1);
				jsonlist.add(jsonObj.toString());
			}
			return jsonlist;
		}else{
			return null;
		}
	}

}
