package com.etc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@GetMapping({"/","login.html"})
	public String gotoIndex(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required=true)String adminname,
			@RequestParam(required=true)String password,HttpServletRequest request){
		return "/part/main2";
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.setAttribute("msg", "您已安全推出系统！");
		request.getSession().invalidate();
		return "login";
	}

}
