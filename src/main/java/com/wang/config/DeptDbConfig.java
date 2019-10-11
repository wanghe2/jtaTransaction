package com.wang.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages="com.wang.dao.dept",sqlSessionFactoryRef="mysql_sqlSessionFactory")
public class DeptDbConfig {
	
	
	@Bean("mysql_sqlSessionFactory")
	public SqlSessionFactory mysql_sqlSessionFactory(@Qualifier("mysqlDataSource")DataSource mysqlDataSource) throws Exception {
		SqlSessionFactoryBean mysql_sqlSession=new SqlSessionFactoryBean();
		mysql_sqlSession.setDataSource(mysqlDataSource);
		Resource configLocation = new ClassPathResource("mybatis-mysql-config.xml"); 
        mysql_sqlSession.setConfigLocation(configLocation);
		mysql_sqlSession.setTypeAliasesPackage("com.wang.bean");
		PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		Resource[] mapperLocations=resolver.getResources("classpath:com/wang/mapper/dept/*.xml");
		mysql_sqlSession.setMapperLocations(mapperLocations);
		return mysql_sqlSession.getObject();
		
	}
	
	/***
	 * 把各自的事务屏蔽掉 ，有一个JtaDbconfig类会配置jta事务
	@Bean("mysqlTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource")DataSource mysqlDataSource) {
		DataSourceTransactionManager mysqlTransactionManager=new DataSourceTransactionManager() ;
		mysqlTransactionManager.setDataSource(mysqlDataSource);
		return mysqlTransactionManager;
	}
	*/
}
