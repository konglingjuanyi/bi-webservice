package com.chengfeng.kj.webservice.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chengfeng.common.dao.BaseNeDaoImpl;
import com.chengfeng.kj.webservice.dao.IEmployeesDao;
import com.chengfeng.kj.webservice.domain.EmployeeVO;
import com.thinkjf.dao.DaoException;

@Repository("employeesDao")
public class EmployeesDaoImpl extends BaseNeDaoImpl implements IEmployeesDao {

	@Override
	public List<EmployeeVO> queryEmployee(Map<String, Object> searchMap) throws DaoException {
		return super.queryForList("EmployeeVO.queryEmployee",searchMap);
	}

	@Override
	public void doDeleteEmployeeBySend(Map<String, Object> searchMap)
			throws DaoException {
		/*deleteRecord("EmployeeVO.deleteEmployeeBySend",searchMap);*/
	}

	@Override
	public List<EmployeeVO> queryUpdateEmployee(Map<String, Object> searchMap)
			throws DaoException {
		return super.queryForList("EmployeeVO.queryUpdateEmployee",searchMap);
	}

}
