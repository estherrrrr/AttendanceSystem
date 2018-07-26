package com.etc.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etc.dao.AdminMapper;
import com.etc.dao.StudentMapper;
import com.etc.dao.TeacherMapper;
import com.etc.entity.Admin;
import com.etc.entity.AdminExample;
import com.etc.entity.JsonResult;
import com.etc.entity.Student;
import com.etc.entity.StudentExample;
import com.etc.entity.Teacher;
import com.etc.entity.TeacherExample;
import com.etc.service.MD5Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("**.do")
public class AdminMainController {
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	TeacherMapper teacherMapper;
	
	//修改密码
	@GetMapping("/restchange")
	public JsonResult changePassword(@RequestParam("newPwd") String newPwd,@RequestParam("oldPwd") String oldPwd,@RequestParam("sex") String sex,@RequestParam("username") String username) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(sex.equals("manager")){
			AdminExample ae=new AdminExample();
			ae.createCriteria().andUsernameEqualTo(username);
			List<Admin> admin=adminMapper.selectByExample(ae);
			Admin a=admin.get(0);
			
			if(a.getPwd().equals(MD5Service.EncoderByMd5(oldPwd))){
				a.setPwd(MD5Service.EncoderByMd5(newPwd));
				adminMapper.updateByPrimaryKeySelective(a);
				return new JsonResult("修改成功！");
			}
			else return new JsonResult("旧密码不正确！");
			
		}
		else if(sex.equals("student")){
			StudentExample se=new StudentExample();
			se.createCriteria().andSnumberEqualTo(username);
			List<Student> student=studentMapper.selectByExample(se);
			Student s=student.get(0);
			if(s.getPwd().equals(MD5Service.EncoderByMd5(oldPwd))){
				s.setPwd(MD5Service.EncoderByMd5(newPwd));
				studentMapper.updateByPrimaryKeySelective(s);
				return new JsonResult("修改成功！");
			}
			else return new JsonResult("旧密码不正确！");
		}
		else{
			TeacherExample te=new TeacherExample();
			te.createCriteria().andTnumberEqualTo(Integer.parseInt(username));
			List<Teacher> teacher=teacherMapper.selectByExample(te);
			Teacher t=teacher.get(0);
			if(t.getPwd().equals(MD5Service.EncoderByMd5(oldPwd))){
				t.setPwd(MD5Service.EncoderByMd5(newPwd));
				teacherMapper.updateByPrimaryKeySelective(t);
				return new JsonResult("修改成功！");
			}
			else return new JsonResult("旧密码不正确！");
		}
	}
	
}
