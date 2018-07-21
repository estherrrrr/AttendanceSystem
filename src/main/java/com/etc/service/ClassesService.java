package com.etc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.ClassesMapper;
import com.etc.dao.ClassesRelationMapper;
import com.etc.entity.Classes;
import com.etc.entity.ClassesExample;


@Service
public class ClassesService {
	@Autowired
	private ClassesMapper classesMapper;
	@Autowired
	private ClassesRelationMapper crMapper;
	
	public boolean doAdd(Classes classes){
		int row = classesMapper.insertSelective(classes);
		return row>0;
	}
	public List<Map<String,Object>> findByCondition(Map<String,Object> param){
		List<Map<String,Object>> list = crMapper.selectClasses(param);
		return list;
	}
}
