package com.etc.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.AdminMapper;
import com.etc.dao.StudentMapper;
import com.etc.dao.TeacherMapper;
import com.etc.entity.AcademyExample;
import com.etc.entity.Admin;
import com.etc.entity.AdminExample;
import com.etc.entity.Student;
import com.etc.entity.StudentExample;
import com.etc.entity.Teacher;
import com.etc.entity.TeacherExample;


@Service
public class MD5Service {
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	        //确定计算方法
	        MessageDigest md5=MessageDigest.getInstance("MD5");
	        BASE64Encoder base64en = new BASE64Encoder();
	        //加密后的字符串
	        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	        return newstr;
	}
	public static boolean checkPassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	        if(EncoderByMd5(newpasswd).equals(oldpasswd))
	            return true;
	        else
	            return false;
	}
	public boolean encodeAllPwd() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		TeacherExample te=new TeacherExample();
		TeacherExample.Criteria tec=te.createCriteria().andPwdEqualTo("123456");
		List<Teacher> tlist=teacherMapper.selectByExample(te);
		if(tlist.size()>0){
			for(int i=0;i<tlist.size();i++){
				Teacher t = tlist.get(i);
				String pwd = t.getPwd();
				t.setPwd(EncoderByMd5(pwd));
				teacherMapper.updateByPrimaryKeySelective(t);
			}
		}
		StudentExample se=new StudentExample();
		StudentExample.Criteria sec=se.createCriteria().andPwdEqualTo("123456");
		List<Student> slist=studentMapper.selectByExample(se);
		if(slist.size()>0){
			for(int i=0;i<slist.size();i++){
				Student s = slist.get(i);
				String pwd = s.getPwd();
				s.setPwd(EncoderByMd5(pwd));
				studentMapper.updateByPrimaryKeySelective(s);
			}
		}
		AdminExample ae=new AdminExample();
		AdminExample.Criteria aec=ae.createCriteria().andPwdEqualTo("123456");
		List<Admin> alist=adminMapper.selectByExample(ae);
		if(alist.size()>0){
			for(int i=0;i<alist.size();i++){
				Admin a = alist.get(i);
				String pwd = a.getPwd();
				a.setPwd(EncoderByMd5(pwd));
				adminMapper.updateByPrimaryKeySelective(a);
			}
		}
		return true;
	}
	 
 
}
