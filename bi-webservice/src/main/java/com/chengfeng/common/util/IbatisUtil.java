package com.chengfeng.common.util;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.result.ResultMapping;

import com.ibatis.sqlmap.engine.mapping.sql.Sql;

import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;

import com.ibatis.sqlmap.engine.scope.SessionScope;

import com.ibatis.sqlmap.engine.scope.StatementScope;

public class IbatisUtil {

	public static final IbatisSql getIbatisSql(String id, SqlMapClient sqlMapClient, Object parameterObject) {
		SqlMapClientImpl sci = (SqlMapClientImpl) sqlMapClient;
		IbatisSql ibatisSql = new IbatisSql();
		MappedStatement mappedStatement = sci.getDelegate().getMappedStatement(id);
		Sql sql = mappedStatement.getSql();
		SessionScope sessionScope = new SessionScope();
		sessionScope.incrementRequestStackDepth();
		StatementScope statementScope = new StatementScope(sessionScope);
		mappedStatement.initRequest(statementScope);
		String strSql = sql.getSql(statementScope, parameterObject);
		strSql = strSql != null ? strSql.trim().toLowerCase() : strSql;
		ibatisSql.setSql(strSql);// 设置SQL语句
		ResultMapping[] resultMappingsArray = statementScope.getResultMap().getResultMappings();// 获得字段信息
		ibatisSql.setResultMappings(resultMappingsArray);
		sql.cleanup(statementScope);
		sessionScope.cleanup();
		mappedStatement = null;
		sql = null;
		sessionScope = null;
		statementScope = null;
		id = null;
		parameterObject = null;
		return ibatisSql;
	}
}
