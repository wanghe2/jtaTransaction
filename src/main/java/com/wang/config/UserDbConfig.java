package com.wang.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages="com.wang.dao.user",sqlSessionFactoryRef="oracle_sqlSessionFactory")
public class UserDbConfig {
	
	@Bean("oracle_sqlSessionFactory")
	@Primary
	public SqlSessionFactory oracle_sqlSessionFactory(@Qualifier("oracleSource")DataSource oracleSource) throws Exception {
		
		SqlSessionFactoryBean oracle_sqlSession=new SqlSessionFactoryBean();
		oracle_sqlSession.setDataSource(oracleSource);
		Resource configLocation = new ClassPathResource("mybatis-oracle-config.xml"); 
        oracle_sqlSession.setConfigLocation(configLocation);
		oracle_sqlSession.setTypeAliasesPackage("com.wang.bean");
		PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		Resource[] mapperLocations=resolver.getResources("classpath:com/wang/mapper/user/*.xml");
		oracle_sqlSession.setMapperLocations(mapperLocations);
		return oracle_sqlSession.getObject();
		
	}
	
	/**
	 * 把各自的事务屏蔽掉 ，有一个JtaDbconfig类会配置jta事务
	 * @Bean("oracleTransactionManager")
	public PlatformTransactionManager oracleTransactionManager(@Qualifier("oracleSource")DataSource oracleSource ) {
		DataSourceTransactionManager oracleTransactionManager=new DataSourceTransactionManager() ;
		oracleTransactionManager.setDataSource(oracleSource);
		return oracleTransactionManager;
	}
	*/
	
	
}
