package com.chengfeng.kj.webservice.dao;

import java.util.List;
import java.util.Map;

import com.chengfeng.kj.webservice.domain.EmployeeVO;
import com.thinkjf.dao.DaoException;

/**
 * 
 * 功能描述：快捷员工信息操作DAO
 *
 * Copyright: Copyright (c) 2013
 *
 * Company: 丞风智能科技有限公司
 * 
 * @author Lei.Zhao
 *
 * @version 2015-8-26
 */
public interface IEmployeesDao {
	/**
	 * 获取员工信息
	 * @param searchMap
	 * @return
	 * @throws DaoException
	 */
	public List<EmployeeVO> queryEmployee(Map<String, Object> searchMap) throws DaoException;
	
	/**
	 * 获取员工更新信息
	 * @param searchMap
	 * @return
	 * @throws DaoException
	 */
	public List<EmployeeVO> queryUpdateEmployee(Map<String, Object> searchMap) throws DaoException;
	
	/**
	 * 删除员工信息
	 * @param searchMap
	 * @throws DaoException
	 */
	public void doDeleteEmployeeBySend(Map<String, Object> searchMap) throws DaoException;
}
