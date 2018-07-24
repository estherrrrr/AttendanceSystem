package com.etc.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.ClassesMapper;
import com.etc.dao.ClassesRelationMapper;
import com.etc.dao.TeacherMapper;
import com.etc.entity.Classes;
import com.etc.entity.ClassesExample;
import com.etc.entity.Teacher;
import com.etc.entity.TeacherExample;


@Service
public class ClassesService {
	@Autowired
	private ClassesMapper classesMapper;
	@Autowired
	private ClassesRelationMapper crMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	
	public List<Integer> findByAid(int aid){
		ClassesExample ce=new ClassesExample();
		ce.createCriteria().andMachinetagEqualTo(aid);
		List<Integer> list=new ArrayList<Integer>();
		List<Classes> classes=classesMapper.selectByExample(ce);
		for(Classes c:classes)
			list.add(c.getTid());
		return list;
	}

	public boolean doAdd(Classes classes){
		TeacherExample te=new TeacherExample();
		TeacherExample.Criteria tec=te.createCriteria().andTnumberEqualTo(classes.getTnumber());
		classes.setTid(teacherMapper.selectByExample(te).get(0).getId());
		int row = classesMapper.insertSelective(classes);
		return row>0;
	}
	public List<Map<String,Object>> findByCondition(Map<String,Object> param){
		List<Map<String,Object>> list = crMapper.selectClasses(param);
		return list;
	}
	
	public boolean doRemove(List<Classes> classes){
		List<Integer> cids= new ArrayList<Integer>();
		for(Classes c:classes)
			cids.add(c.getId());
		ClassesExample ce=new ClassesExample();
		ce.createCriteria().andIdIn(cids);
		int row = classesMapper.deleteByExample(ce);
		return row==cids.size();
	}
	
	public boolean doModify(Classes classes){
		TeacherExample te=new TeacherExample();
		TeacherExample.Criteria tec=te.createCriteria().andTnumberEqualTo(classes.getTnumber());
		classes.setTid(teacherMapper.selectByExample(te).get(0).getId());
		int row = classesMapper.updateByPrimaryKeySelective(classes);
		return row>0;
	}
	
}
