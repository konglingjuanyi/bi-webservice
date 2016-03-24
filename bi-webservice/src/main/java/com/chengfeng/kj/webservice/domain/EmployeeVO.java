package com.chengfeng.kj.webservice.domain;

import java.io.Serializable;

/**
 * 
 * 功能描述：快捷员工信息实体类
 *
 * Copyright: Copyright (c) 2013
 *
 * Company: 丞风智能科技有限公司
 * 
 * @author Lei.Zhao
 *
 * @version 2015-8-26
 */
public class EmployeeVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 910766873362177549L;
	
	private String employeeCode;
	
	private String employeeName;
	
	private String deptName;
	
	private String ownerSite;
	
	private String jobName;
	
	private String phoneSms;
	
	private String phone;
	
	private String barPassword;
	
	private String operFalg;
	
	private Integer checkState;
	
	private String alipay;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOwnerSite() {
		return ownerSite;
	}

	public void setOwnerSite(String ownerSite) {
		this.ownerSite = ownerSite;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getPhoneSms() {
		return phoneSms;
	}

	public void setPhoneSms(String phoneSms) {
		this.phoneSms = phoneSms;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBarPassword() {
		return barPassword;
	}

	public void setBarPassword(String barPassword) {
		this.barPassword = barPassword;
	}

	public String getOperFalg() {
		return operFalg;
	}

	public void setOperFalg(String operFalg) {
		this.operFalg = operFalg;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	
}
