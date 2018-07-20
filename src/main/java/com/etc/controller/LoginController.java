package com.etc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etc.dao.AdminMapper;
import com.etc.entity.Admin;
import com.etc.entity.AdminExample;

@Controller
public class LoginController {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@GetMapping({"/","login.html"})
	public String gotoIndex(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required=true)String adminname,
			@RequestParam(required=true)String password,HttpServletRequest request){
		AdminExample ae = new AdminExample();
		ae.createCriteria().andUsernameEqualTo(adminname).andPwdEqualTo(password);
		List<Admin> admins = adminMapper.selectByExample(ae);
		if(admins.size()!=1){
			request.setAttribute("msg", "您输入的用户名或密码错误！");
			return "login";
		}else{
			request.getSession().setAttribute("admin", admins.get(0));
			return "adminMain";
		}
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.setAttribute("msg", "您已安全推出系统！");
		request.getSession().invalidate();
		return "login";
	}

}
