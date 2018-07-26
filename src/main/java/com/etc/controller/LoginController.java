package com.etc.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
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
import com.etc.hadoop.*;
import com.etc.service.AcademyService;
import com.etc.service.AttendanceService;
import com.etc.service.ClassesService;
import com.etc.service.MD5Service;

@Controller
public class LoginController {
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private MD5Service md5Service;
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private ClassesService classesService;
	@Autowired
	private AcademyService academyService;
	
	@GetMapping({"/","login.html"})
	public String gotoIndex(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required=true)String adminname,
			@RequestParam(required=true)String password,@RequestParam(required=true)String sex,HttpServletRequest request) throws NoSuchAlgorithmException, IOException, ClassNotFoundException, URISyntaxException, InterruptedException{
		request.getSession().setAttribute("sex",sex);
		//对密码进行md5加密
		password = md5Service.EncoderByMd5(password);
		if(sex.equals("manager")){
			AdminExample ae = new AdminExample();
			ae.createCriteria().andUsernameEqualTo(adminname).andPwdEqualTo(password);
			List<Admin> admins = adminMapper.selectByExample(ae);
			if(admins.size()!=1){
				request.setAttribute("msg", "您输入的用户名或密码错误！");
				return "login";
			}else{
				//登录成功后，进行统计
				List<Attendance> attendances=attendanceService.findAll();
				FileOutputStream fos = new FileOutputStream("./src/main/resources/static/download/attendance.txt");
				OutputStreamWriter writer = new OutputStreamWriter(fos);
				for(Attendance a:attendances){
					 writer.write(a.getSnumber() + "\t");
					 writer.write(String.valueOf(a.getCid()) + "\t");
					 writer.write(String.valueOf(a.getAstatus()) + "\t");
		             writer.write(a.getAdate()+"\r\n");
				}
				writer.flush();
		        writer.close();
		         
		        SchoolCount.test("attendance.txt");
		        AcademyCount.test("attendance.txt");
		        AcademyDetailCount.test("attendance.txt");
				TopClassesCount.test("attendance.txt");

				request.getSession().setAttribute("admin", admins.get(0));
				return "adminMain";
			}
		}
		else if(sex.equals("teacher")){
			TeacherExample te=new TeacherExample();
			int tnumber = 0;
			try{
				tnumber = Integer.parseInt(adminname);
			}
			catch(NumberFormatException ex){
				request.setAttribute("msg", "用户名格式错误！");
				return "login";
			}
			te.createCriteria().andTnumberEqualTo(tnumber).andPwdEqualTo(password);
			List<Teacher> teachers = teacherMapper.selectByExample(te);
			if(teachers.size()!=1){
				request.setAttribute("msg", "您输入的用户名或密码错误！");
				return "login";
			}else{
				//登录成功后，进行统计
				List<Attendance> attendances=attendanceService.findAll();
				FileOutputStream fos = new FileOutputStream("./src/main/resources/static/download/attendance.txt");
				OutputStreamWriter writer = new OutputStreamWriter(fos);
				for(Attendance a:attendances){
					 writer.write(a.getSnumber() + "\t");
					 writer.write(String.valueOf(a.getCid()) + "\t");
					 writer.write(String.valueOf(a.getAstatus()) + "\t");
		             writer.write(a.getAdate()+"\r\n");
				}
				writer.flush();
		        writer.close();
		         
		        SchoolCount.test("attendance.txt");
		        AcademyCount.test("attendance.txt");
		        AcademyDetailCount.test("attendance.txt");
				TopClassesCount.test("attendance.txt");
				
				request.getSession().setAttribute("admin", teachers.get(0));
				return "teacherMain";
			}
		}else{
			StudentExample se=new StudentExample();
			se.createCriteria().andSnumberEqualTo(adminname).andPwdEqualTo(password);
			List<Student> students = studentMapper.selectByExample(se);
			if(students.size()!=1){
				request.setAttribute("msg", "您输入的用户名或密码错误！");
				return "login";
			}else{
				//登录成功后，进行统计
				List<Attendance> attendances=attendanceService.findAll();
				FileOutputStream fos = new FileOutputStream("./src/main/resources/static/download/attendance.txt");
				OutputStreamWriter writer = new OutputStreamWriter(fos);
				for(Attendance a:attendances){
					 writer.write(a.getSnumber() + "\t");
					 writer.write(String.valueOf(a.getCid()) + "\t");
					 writer.write(String.valueOf(a.getAstatus()) + "\t");
		             writer.write(a.getAdate()+"\r\n");
				}
				writer.flush();
		        writer.close();
		         
		        SchoolCount.test("attendance.txt");
		        AcademyCount.test("attendance.txt");
		        AcademyDetailCount.test("attendance.txt");
				TopClassesCount.test("attendance.txt");
				StudentEverydayCount.test("attendance.txt");
				
				request.getSession().setAttribute("admin", students.get(0));
				return "studentMain";
			}
		}
		
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.setAttribute("msg", "您已安全退出系统！");
		request.getSession().invalidate();
		return "login";
	}
	
	//修改密码界面跳转
	@RequestMapping("pwd")
	public String passwordPage(HttpServletRequest request){
		String sex=(String) request.getSession().getAttribute("sex");
		if(sex.equals("manager"))
			return "adminPwd";
		else if(sex.equals("teacher"))
			return "teacherPwd";
		else
			return "studentPwd";
	}
	
	//相应学院统计结果跳转
	@RequestMapping("/academy")
	public String showMyAca(@RequestParam String academy,HttpServletRequest request){
		request.getSession().setAttribute("academy", academy);
		String sex=(String) request.getSession().getAttribute("sex");
		if(sex.equals("manager"))
			return "academyDetail";
		else if(sex.equals("teacher"))
			return "teacheracademyDetail";
		else
			return "studentacademyDetail";
	}
}
