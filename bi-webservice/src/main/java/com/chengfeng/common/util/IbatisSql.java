package com.chengfeng.common.util;

import com.ibatis.sqlmap.engine.mapping.result.ResultMapping;

public class IbatisSql {
	
	private String sql="";
	
	private ResultMapping[] resultMappings=null;
	
	public final String getSql(){
		return sql;
	}
	
	public final void setSql(String sql){
		if(sql!=null){
			this.sql=sql;
		}
	}
	
	public final ResultMapping[] getResultMapping(){
		return resultMappings;
	}
	
	public final void setResultMappings(ResultMapping[] resultMappings){
		if(resultMappings !=null){
			this.resultMappings=resultMappings;
		}
	}
	
}
