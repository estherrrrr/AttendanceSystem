package com.etc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etc.dao.AcademyMapper;
import com.etc.entity.Academy;

import com.etc.entity.JsonResult;

@RestController
@RequestMapping("**.do")
public class AcademyController {
	@Autowired
	private AcademyMapper academyMapper;
	
	@GetMapping("/restacademy")
	public JsonResult<Academy> getAcademy(){
		System.out.println("11111");
		List<Academy> list = academyMapper.selectByExample(null);
		
		return new JsonResult<Academy>(list);
	}
	
}
