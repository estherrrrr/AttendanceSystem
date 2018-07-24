package com.etc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.CourseMapper;
import com.etc.dao.StudentMapper;
import com.etc.entity.Course;
import com.etc.entity.CourseExample;
import com.etc.entity.Student;
import com.etc.entity.StudentExample;


@Service
public class FileService {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();     
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
	public  boolean fileRead(String filename,int cid) throws IOException {
    	File file=new File("./src/main/resources/static/download/"+filename);
    	InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"GBK"); 
    	BufferedReader br = new BufferedReader(reader); 
    	String line = "";  
        line = br.readLine();
        StudentExample se=new StudentExample();
        CourseExample ce=new CourseExample();
        while (line != null) {
        	line = br.readLine(); // 一次读入一行数据
        	String[] str=line.split(",");
        	System.out.println(line);
        	se.createCriteria().andSnumberEqualTo(str[0]);
        	List<Student> s=studentMapper.selectByExample(se);
        	if(s.size()==0){
        		Student student=new Student();
        		student.setSname(str[1]);
        		student.setSnumber(str[0]);
        		student.setAid(Integer.parseInt(str[2]));
        		student.setPwd("123456");
        		studentMapper.insertSelective(student);
        		Course course=new Course();
        		course.setCid(cid);
        		course.setSnumber(student.getSnumber());
        		courseMapper.insertSelective(course);
        	}
        	else{
        		ce.createCriteria().andCidEqualTo(cid).andSnumberEqualTo(str[0]);
        		List<Course> c = courseMapper.selectByExample(ce);
        		if(c.size()==0){
        			Course course=new Course();
            		course.setCid(cid);
            		course.setSnumber(str[0]);
            		courseMapper.insertSelective(course);
        		}		
        	}            
        }
		return true;  
    }
}
