package com.wang.config;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.wang.util.DbPropertiesByAuto;
 
/**
 * Druid配置
 *	
 *由于atomikosDatasource 与mysql 有些地方有不兼容，所以 两个数据库都是oracle类型的
 * 
 */
@Configuration
public class JtaDbConfig {
    
	
	@Autowired
	DbPropertiesByAuto dbPropertiesByAuto;
	
	@Bean("mysqlDataSource")
	@Primary
	public DataSource mysqlDataSource() throws SQLException {
		
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build("mysql");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("mysqlDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
	}
	
	@Bean("oracleSource")
	public DataSource oracleSource() {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build("oracle");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("oracleDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
	}
	
	
    /**
     * 注入事物管理器
     * @return
     * @throws SystemException 
     */
    @Bean(name = "xatx")
    public JtaTransactionManager regTransactionManager () throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(300);
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
 
 
    private Properties build(String way) {
    	Properties prop = new Properties();
    	if("mysql".equals(way)) {
    		prop.put("url", dbPropertiesByAuto.mysqlurl);
            prop.put("username", dbPropertiesByAuto.mysqlusername);
            prop.put("password", dbPropertiesByAuto.mysqlpwd);
            prop.put("driverClassName", dbPropertiesByAuto.mysqldirver);
        }else {
    		prop.put("url", dbPropertiesByAuto.oracleurl);
            prop.put("username", dbPropertiesByAuto.oracleusername);
            prop.put("password", dbPropertiesByAuto.oraclepwd);
            prop.put("driverClassName", dbPropertiesByAuto.oracledirver);
    	}
        prop.put("maxActive", dbPropertiesByAuto.maxActive);
        return prop;
    }
 
}