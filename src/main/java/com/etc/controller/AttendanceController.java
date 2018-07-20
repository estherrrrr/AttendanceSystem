package com.etc.controller;

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
	public JsonResult<Map<String, Double>> showSchool(){
		Map<String,Double> map=new HashMap<String,Double>();
		map.put("2018-9-1", 97.0);
		map.put("2018-9-2", 99.0);
		map.put("2018-9-3", 94.9);

		JsonResult<Map<String,Double>> list=new JsonResult<Map<String,Double>>(map);
		return list;
	}
}
