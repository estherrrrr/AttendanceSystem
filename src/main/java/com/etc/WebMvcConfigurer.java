package com.etc;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.etc.interceptor.SessionInterceptor;

@SpringBootConfiguration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	//添加拦截器
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**.do");
	}
}
