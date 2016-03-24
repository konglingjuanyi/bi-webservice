package com.chengfeng.kj.webservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Service;

import com.chengfeng.kj.webservice.dao.IEmployeesDao;
import com.chengfeng.kj.webservice.domain.EmployeeVO;
import com.chengfeng.kj.webservice.service.IEmployeesService;
import com.thinkjf.service.ServiceException;

@Service("employeesService")
public class EmployeesServiceImpl implements IEmployeesService {

	@Resource(name ="employeesDao")
    private IEmployeesDao employeesDao;
	
	@Override
	public List<EmployeeVO> queryEmployee(Map<String, Object> searchMap) throws ServiceException {
		return employeesDao.queryEmployee(searchMap);
	}

	@Override
	public void doDeleteEmployeeBySend(Map<String, Object> searchMap)
			throws ServiceException {
		employeesDao.doDeleteEmployeeBySend(searchMap);
	}

	@Override
	public List<EmployeeVO> queryUpdateEmployee(Map<String, Object> searchMap)
			throws ServiceException {
		return employeesDao.queryUpdateEmployee(searchMap);
	}

	@Override
	public List<String> getBusinessParams(Map<String, Object> searchMap)
			throws ServiceException {
		List<EmployeeVO> list = employeesDao.queryUpdateEmployee(searchMap);
		if(list.size()>0){
			List<String> jsonlist = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObj = new JSONObject();
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
				jsonObj.put("PHONE", "/");
				jsonObj.put("PDA_PWD", list.get(i).getBarPassword()==null?"/":list.get(i).getBarPassword());
				jsonObj.put("RD_STATUS", list.get(i).getOperFalg()==null?"":list.get(i).getOperFalg());
				jsonObj.put("ALIPAY_NO", list.get(i).getAlipay()==null?"":list.get(i).getAlipay());
				jsonObj.put("WECHAT_NO", "");
				jsonObj.put("REAL_NAME_STATUS", list.get(i).getCheckState()==null?"":list.get(i).getCheckState());
				jsonlist.add(jsonObj.toString());
			}
			return jsonlist;
		}else{
			return null;
		}
	}

}
