package com.etc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.AcademyMapper;
import com.etc.entity.Academy;
import com.etc.entity.AcademyExample;

@Service
public class AcademyService {
	@Autowired
	private AcademyMapper academyMapper;
	
	public List<Academy> findAll(){
		return academyMapper.selectByExample(null);
	}
	public Academy findByAname(String aname){
		AcademyExample ae=new AcademyExample();
		ae.createCriteria().andAnameEqualTo(aname);
		List<Academy> list=academyMapper.selectByExample(ae);
		if(list.size()>0)return list.get(0);
		else return null;
	}
}
