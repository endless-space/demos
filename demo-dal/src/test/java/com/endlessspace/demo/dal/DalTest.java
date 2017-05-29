package com.endlessspace.demo.dal;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.endlessspace.demo.dal.model.Employee;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DalTest {
	
	@Test
	public void testDal() throws Exception {
		Properties props = new Properties();
		props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hikari.properties"));
		
		HikariConfig config = new HikariConfig(props);
		HikariDataSource ds = new HikariDataSource(config);
		Sql2o sql2o = new Sql2o(ds);
		
		Connection conn = sql2o.open();
		List<Employee> employees = conn
				.createQuery("select * from t_ucenter_user_employee")
				.addColumnMapping("", "")
				.executeAndFetch(Employee.class);
		LOG.info("{}", employees);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(DalTest.class);
}
