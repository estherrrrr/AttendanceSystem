package com.etc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etc.dao.AdminMapper;
import com.etc.dao.StudentMapper;
import com.etc.dao.TeacherMapper;
import com.etc.entity.*;

@Controller
public class LoginController {
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@GetMapping({"/","login.html"})
	public String gotoIndex(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required=true)String adminname,
			@RequestParam(required=true)String password,@RequestParam(required=true)String sex,HttpServletRequest request){

		if(sex.equals("manager")){
			AdminExample ae = new AdminExample();
			ae.createCriteria().andUsernameEqualTo(adminname).andPwdEqualTo(password);
			List<Admin> admins = adminMapper.selectByExample(ae);
			if(admins.size()!=1){
				request.setAttribute("msg", "您输入的用户名或密码错误！");
				return "login";
			}else{
				request.getSession().setAttribute("admin", admins.get(0));
				return "test";
			}
		}
		else if(sex.equals("teacher")){
			TeacherExample te=new TeacherExample();
			te.createCriteria().andTnameEqualTo(adminname).andPwdEqualTo(password);
			List<Teacher> teachers = teacherMapper.selectByExample(te);
			if(teachers.size()!=1){
				request.setAttribute("msg", "您输入的用户名或密码错误！");
				return "login";
			}else{
				request.getSession().setAttribute("admin", teachers.get(0));
				return "teacherMain";
			}
		}else{
			StudentExample se=new StudentExample();
			se.createCriteria().andSnameEqualTo(adminname).andPwdEqualTo(password);
			List<Student> students = studentMapper.selectByExample(se);
			if(students.size()!=1){
				request.setAttribute("msg", "您输入的用户名或密码错误！");
				return "login";
			}else{
				request.getSession().setAttribute("admin", students.get(0));
				return "studentMain";
			}
		}
		
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.setAttribute("msg", "您已安全推出系统！");
		request.getSession().invalidate();
		return "login";
	}

	@RequestMapping("/academy")
	public String showMyAca(@RequestParam String academy,HttpServletRequest request){
		
		request.getSession().setAttribute("academy", academy);
		return "academyDetail";
	}
}
