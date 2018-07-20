package com.etc;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

@MapperScan("com.etc.dao")
@EnableTransactionManagement
@SpringBootApplication
public class AttendanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceSystemApplication.class, args);
	}
	
	@Bean
	public PageInterceptor pageHelper(){
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.put("helperDialect", "mysql");
		properties.put("reasonable", "true");
		properties.put("offsetAsPageNum", "true");
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}
}
