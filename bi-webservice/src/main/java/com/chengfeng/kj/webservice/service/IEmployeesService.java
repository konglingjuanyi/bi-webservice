package com.chengfeng.kj.webservice.service;

import java.util.List;
import java.util.Map;

import com.chengfeng.kj.webservice.domain.EmployeeVO;
import com.thinkjf.service.ServiceException;

/**
 * 
 * 功能描述：快捷员工信息操作SERVICE
 *
 * Copyright: Copyright (c) 2013
 *
 * Company: 丞风智能科技有限公司
 * 
 * @author Lei.Zhao
 *
 * @version 2015-8-26
 */
public interface IEmployeesService {
	
	/**
	 * 获取员工信息
	 * @param searchMap
	 * @return
	 * @throws ServiceException
	 */
	public List<EmployeeVO> queryEmployee(Map<String, Object> searchMap) throws ServiceException;
	
	/**
	 * 获取员工更新信息
	 * @param searchMap
	 * @return
	 * @throws ServiceException
	 */
	public List<EmployeeVO> queryUpdateEmployee(Map<String, Object> searchMap) throws ServiceException;
	
	/**
	 * 删除员工信息
	 * @param searchMap
	 * @throws ServiceException
	 */
	public void doDeleteEmployeeBySend(Map<String, Object> searchMap) throws ServiceException;
	
	/**
	 * 构造接口业务参数
	 * @param list
	 * @return
	 * @throws ServiceException   
	 * @author Pippo
	 */
	public List<String> getBusinessParams(Map<String, Object> searchMap) throws ServiceException;
}
