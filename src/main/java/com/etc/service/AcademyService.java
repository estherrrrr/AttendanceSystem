package com.etc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.AcademyMapper;
import com.etc.entity.Academy;

@Service
public class AcademyService {
	@Autowired
	private AcademyMapper academyMapper;
	
	public List<Academy> findAll(){
		return academyMapper.selectByExample(null);
	}
}
