package com.etc.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.AcademyMapper;
import com.etc.dao.ClassesMapper;
import com.etc.dao.ClassesRelationMapper;
import com.etc.dao.TeacherMapper;
import com.etc.dao.TeacherRelationMapper;
import com.etc.entity.AcademyExample;
import com.etc.entity.Classes;
import com.etc.entity.ClassesExample;
import com.etc.entity.Teacher;
import com.etc.entity.TeacherExample;


@Service
public class TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private TeacherRelationMapper trMapper;
	
	@Autowired
	private AcademyMapper academyMapper;
	
	@Autowired
	private ClassesMapper classesMapper;
	
	public boolean doAdd(Teacher teacher){
		TeacherExample te=new TeacherExample();
		TeacherExample.Criteria tec=te.createCriteria().andTnumberEqualTo(teacher.getTnumber());
		List<Teacher> list=teacherMapper.selectByExample(te);
		if(list.size()>0) return false;
		AcademyExample ae=new AcademyExample();
		AcademyExample.Criteria aec=ae.createCriteria().andAnameEqualTo(teacher.getAname());
		teacher.setAid(academyMapper.selectByExample(ae).get(0).getId());
		teacher.setPwd("123456");
		int row = teacherMapper.insertSelective(teacher);
		return row>0;
	}
	public List<Map<String,Object>> findByCondition(Map<String,Object> param){
		List<Map<String,Object>> list = trMapper.selectTeacher(param);
		return list;
	}
	
	public boolean doRemove(List<Teacher> teacher){
		List<Integer> tids= new ArrayList<Integer>();
		
		for(Teacher t:teacher)
		{
			ClassesExample ce=new ClassesExample();
			ce.createCriteria().andTidEqualTo(t.getId());
			List<Classes> classes=classesMapper.selectByExample(ce);
			List<Integer>cids=new ArrayList<Integer>();
			for(Classes c : classes)
				cids.add(c.getId());
			ce.createCriteria().andIdIn(cids);
			int row1=classesMapper.deleteByExample(ce);
			if(row1!=cids.size())
				return false;
			tids.add(t.getId());
			
		}
		TeacherExample te=new TeacherExample();
		te.createCriteria().andIdIn(tids);
		int row = teacherMapper.deleteByExample(te);
		return row==tids.size();
	}
	
	public boolean doModify(Teacher teacher){
		TeacherExample te=new TeacherExample();
		TeacherExample.Criteria tec=te.createCriteria().andTnumberEqualTo(teacher.getTnumber());
		List<Teacher> list=teacherMapper.selectByExample(te);
		if(list.size()>0&&list.get(0).getId()!=teacher.getId()) return false;
		AcademyExample ae=new AcademyExample();
		AcademyExample.Criteria aec=ae.createCriteria().andAnameEqualTo(teacher.getAname());
		teacher.setAid(academyMapper.selectByExample(ae).get(0).getId());
		int row = teacherMapper.updateByPrimaryKeySelective(teacher);
		return row>0;
	}
	
}
