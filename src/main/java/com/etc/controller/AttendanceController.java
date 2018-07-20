package com.etc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etc.entity.JsonResult;

@RestController
@RequestMapping("**.do")
public class AttendanceController {

	@GetMapping("/showschool")
	public JsonResult<List<String>> showSchool(){
		List<String> map=new ArrayList<String>();
		map.add("97.0");map.add("93.8");
		

		JsonResult<List<String>> list=new JsonResult<List<String>>(map);
		return list;
	}
}
