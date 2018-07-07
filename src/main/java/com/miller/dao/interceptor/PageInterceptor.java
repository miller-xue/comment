package com.miller.dao.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.miller.bean.BaseBean;
import com.miller.bean.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * @author Miller
 *	分页拦截器
 */

@Intercepts(value = {@Signature(type = StatementHandler.class , method="prepare", args= { Connection.class} )})
public class PageInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		//获得拦截对象目标
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();

		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());

		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		//配置文件中sql的id
		String sqlId = mappedStatement.getId();
		if(sqlId.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			// 获得原始sql语句
			String sql = boundSql.getSql();

			//查询总条数
			String countSql = "select count(*) from (" + sql + ")a";

			Connection conn = (Connection)invocation.getArgs()[0];
			PreparedStatement countStatement =  conn.prepareStatement(countSql);
			//注入参数
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();

			//获取参数
			BaseBean baseBean = (BaseBean)boundSql.getParameterObject();

			Page page = baseBean.getPage();

			if(rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			// 获得参数map

			String pageSql = sql + " limit " + (page.getCurrentPage() - 1) * page.getPageNumber() + "," + page.getPageNumber();
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {

		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

}
