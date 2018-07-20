package com.etc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("**.do")
public class CommonController {
	
	//通配处理，一般用于menu转发页面
	@GetMapping("/*")
	public String doForward(HttpServletRequest request){
		String url = request.getServletPath();    //statistcal.do
		String pathname = url.substring(0,url.indexOf("."));   //statistcal
		return pathname;
	}

}
