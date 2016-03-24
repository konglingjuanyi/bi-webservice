package com.chengfeng.kj.webservice.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chengfeng.common.util.HttpClientUtils;
import com.chengfeng.kj.webservice.domain.EmployeeVO;
import com.chengfeng.kj.webservice.domain.SiteVO;
import com.chengfeng.kj.webservice.service.IEmployeesService;
import com.chengfeng.kj.webservice.service.ISitesService;
import com.thinkjf.core.config.GlobalConfig;
import com.thinkjf.service.ServiceException;

/**
 * 快捷接口调用
 * 
 * 功能描述：
 *
 * Copyright: Copyright (c) 2013
 *
 * Company: 丞风智能科技有限公司
 * 
 * @author Lei.Zhao
 *
 * @version 2015-8-26
 */
public class KjClient {
	
	//@Autowired
	private IEmployeesService employeesService;
	
	//@Autowired
	private ISitesService sitesService;
	
	private final Log log = LogFactory.getLog(getClass());
	
	/**
	 * 获取员工信息调用接口
	 * @throws HttpException
	 * @throws IOException
	 */
	public void sendEmployee() throws HttpException, IOException {
		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setConnectionManagerTimeout(1000);
		HttpClient client = new HttpClient(httpClientParams, new SimpleHttpConnectionManager(true));
		Map<String, Object> searchMap = new HashMap<String, Object>();
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();
		try {
			List<EmployeeVO> list = employeesService.queryEmployee(searchMap);
			if(list.size()>0){
				JSONObject jsonObj = new JSONObject();
				List<String> jsonlist = new ArrayList<String>();
				PostMethod postMethod = new PostMethod(GlobalConfig.getPropertyValue("common.kjwebservice.employeeupdate.url"));
				for(int i=0;i<list.size();i++){
					postMethod.getParams().clear();
					postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
					
					jsonObj.put("EMPLOYEE_CODE", list.get(i).getEmployeeCode());
					jsonObj.put("EMPLOYEE_NAME", list.get(i).getEmployeeName());
					jsonObj.put("SITE_CODE", list.get(i).getOwnerSite()==null?"/":list.get(i).getOwnerSite());
					if(list.get(i).getDeptName()!=null){
						String[] dn = list.get(i).getDeptName().split("/");
						if(dn.length>1){
							jsonObj.put("DEPT_NAME", dn[0]);
							jsonObj.put("JOB_NAME", dn[1]);
						}else{
							jsonObj.put("DEPT_NAME", dn[0]);
							jsonObj.put("JOB_NAME", "员工");
						}
					}else{
						jsonObj.put("DEPT_NAME", "/");
						jsonObj.put("JOB_NAME", "员工");
					}
					jsonObj.put("PHONE_SMS", "/");
					jsonObj.put("PHONE", list.get(i).getPhone()==null?"/":list.get(i).getPhone());
					jsonObj.put("PDA_PWD", list.get(i).getBarPassword()==null?"/":list.get(i).getBarPassword());
					jsonObj.put("RD_STATUS", list.get(i).getOperFalg()==null?"":list.get(i).getOperFalg());
					
					jsonlist.add(jsonObj.toString());
					
				}
				JSONArray jsonArray = new JSONArray();
				jsonArray = JSONArray.fromObject(jsonlist);
				
				nameValueList.add(new NameValuePair("params", jsonArray.toString()));
				postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				postMethod.setRequestBody(nameValueList.toArray(new NameValuePair[nameValueList.size()]));
				postMethod.addRequestHeader("Connection", "close");
				client.executeMethod(postMethod);
				
				System.out.println("Login form post: "+ postMethod.getStatusLine().getStatusCode());//请求服务器返回状态
				String responsess = postMethod.getResponseBodyAsString();
				JSONObject jsonresult = JSONObject.fromObject(responsess);
				String code = jsonresult.getString("code");
				if(code.equals("200")){
					for(int i=0;i<list.size();i++){
						searchMap.put("employeeCode", list.get(i).getEmployeeCode());
						employeesService.doDeleteEmployeeBySend(searchMap);
					}
				}
				String message = jsonresult.getString("message");
				System.out.println("code:"+code+"  message:"+message);
			}else{
				log.info("暂无数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[Error:调用接口失败]", e);
		}
	}
	
	/**
	 * 获取网点信息调用接口
	 * @throws HttpException
	 * @throws IOException
	 */
	public void sendSite() throws HttpException, IOException {
		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setConnectionManagerTimeout(1000);
		
		HttpClient client = new HttpClient(httpClientParams, new SimpleHttpConnectionManager(true));
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();
		try {
			List<SiteVO> list = sitesService.querySite(searchMap);
			if(list.size()>0){
				JSONObject jsonObj = new JSONObject();
				PostMethod postMethod = new PostMethod(GlobalConfig.getPropertyValue("common.kjwebservice.siteupdate.url"));
				List<String> jsonlist = new ArrayList<String>();
				for(int i=0;i<list.size();i++){
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
							jsonObj.put("FIRST_CENTER_SITE_CODE", list.get(i).getSuperiorSite()==null?"":list.get(i).getSuperiorSite());
						}else{
							String superCentersitesign = list.get(i).getSuperCentersitesign()==null?"":list.get(i).getSuperCentersitesign();
							if(superCentersitesign.equals("1")){
								jsonObj.put("SITE_TYPE_NAME", "一级加盟网点");
								jsonObj.put("FIRST_CENTER_SITE_CODE", list.get(i).getSuperiorSite()==null?"":list.get(i).getSuperiorSite());
							}else{
								jsonObj.put("SITE_TYPE_NAME", "二级加盟网点");
								jsonObj.put("FIRST_CENTER_SITE_CODE", list.get(i).getFirstCenterSiteCode()==null?"":list.get(i).getFirstCenterSiteCode());
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
				JSONArray jsonArray = new JSONArray();
				jsonArray = JSONArray.fromObject(jsonlist);
				
				nameValueList.add(new NameValuePair("params", jsonArray.toString()));
				postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				postMethod.setRequestBody(nameValueList.toArray(new NameValuePair[nameValueList.size()]));
				postMethod.addRequestHeader("Connection", "close");
				
				client.executeMethod(postMethod);
				System.out.println("Login form post: "+ postMethod.getStatusLine().getStatusCode());//请求服务器返回状态
				String responsess = postMethod.getResponseBodyAsString();
				JSONObject jsonresult = JSONObject.fromObject(responsess);
				String code = jsonresult.getString("code");
				if(code.equals("200")){
					for(int i=0;i<list.size();i++){
						searchMap.put("siteCode", list.get(i).getSiteCode());
						sitesService.doDeleteSiteBySend(searchMap);
					}
				}
				String message = jsonresult.getString("message");
				System.out.println("code:"+code+"  message:"+message);
			}else{
				log.info("暂无数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[Error:调用接口失败]", e);
		}
		
	}
	
	/**
	 * 发送网点更新数据
	 * @throws HttpException
	 * @throws IOException
	 */
	public void sendUpdateSite() throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		List<String> jsonlist = new ArrayList<String>();
		try {
			jsonlist = sitesService.getBusinessParams(searchMap);
			if(jsonlist == null || jsonlist.size() < 0){
				log.info("未查询到网点更新数据");
				return;
			}
		} catch (ServiceException e) {
			log.error("查询数据异常："+e.getMessage(), e);
			return;
		}
		//循环调用
		int num = 1;
		while(num <= 5){
			try {
				String url = GlobalConfig.getPropertyValue("common.kjwebservice.siteupdate.url");
				String result = HttpClientUtils.sendHttpReq(jsonlist,url);
				log.info("网点同步接口通讯： "+ result);
				return;
			} catch (Exception e) {
				try {
					num++;
					log.info("网点同步连接异常，开始等待180秒后开始第"+num+"次调用接口");
					Thread.sleep(3*60*1000);//休眠180秒后重新调用
				} catch (InterruptedException e1) {
					log.error(e.getMessage(), e1);
				}
			}
		}
	}
	
	public void sendUpdateEmployee() throws HttpException, IOException {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		List<String> jsonlist = new ArrayList<String>();
		try {
			jsonlist = employeesService.getBusinessParams(searchMap);
			if(jsonlist == null || jsonlist.size() < 0){
				log.info("未查询到员工更新数据");
				return;
			}
		} catch (ServiceException e) {
			log.error("查询数据异常："+e.getMessage(), e);
			return;
		}
		//循环调用
		int num = 1;
		while(num <= 5){
			try {
				String url = GlobalConfig.getPropertyValue("common.kjwebservice.employeeupdate.url");
				String result = HttpClientUtils.sendHttpReq(jsonlist,url);
				log.info("员工同步接口通讯： "+ result);
				return;
			} catch (Exception e) {
				log.error("[Error:调用接口失败]", e);
				try {
					num++;
					log.info("员工同步连接异常，开始等待180秒后开始第"+num+"次调用接口");
					Thread.sleep(3*60*1000);//休眠180秒后重新调用
				} catch (InterruptedException e1) {
					log.error(e.getMessage(), e1);
				}
			}
		}
	}
}
