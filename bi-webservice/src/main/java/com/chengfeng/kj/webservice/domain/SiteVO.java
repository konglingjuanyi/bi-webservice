package com.chengfeng.kj.webservice.domain;

import java.io.Serializable;

/**
 * 
 * 功能描述：快捷网点信息实体类
 *
 * Copyright: Copyright (c) 2013
 *
 * Company: 丞风智能科技有限公司
 * 
 * @author Lei.Zhao
 *
 * @version 2015-8-26
 */
public class SiteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6668382625871015456L;
	
	private String siteCode;
	
	private String siteName;
	
	private String superiorSite;
	
	private String superiorFinanceCenter;
	
	private String type;
	
	private Integer blNotInput;
	
	private String province;
	
	private String city;
	
	private String rangeName;
	
	private String areaName;
	
	private String centersitesign;
	
	private String superCentersitesign;
	
	private String firstCenterSiteCode;

	public String getFirstCenterSiteCode() {
		return firstCenterSiteCode;
	}

	public void setFirstCenterSiteCode(String firstCenterSiteCode) {
		this.firstCenterSiteCode = firstCenterSiteCode;
	}

	public String getCentersitesign() {
		return centersitesign;
	}

	public void setCentersitesign(String centersitesign) {
		this.centersitesign = centersitesign;
	}

	public String getSuperCentersitesign() {
		return superCentersitesign;
	}

	public void setSuperCentersitesign(String superCentersitesign) {
		this.superCentersitesign = superCentersitesign;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSuperiorSite() {
		return superiorSite;
	}

	public void setSuperiorSite(String superiorSite) {
		this.superiorSite = superiorSite;
	}

	public String getSuperiorFinanceCenter() {
		return superiorFinanceCenter;
	}

	public void setSuperiorFinanceCenter(String superiorFinanceCenter) {
		this.superiorFinanceCenter = superiorFinanceCenter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBlNotInput() {
		return blNotInput;
	}

	public void setBlNotInput(Integer blNotInput) {
		this.blNotInput = blNotInput;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRangeName() {
		return rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
