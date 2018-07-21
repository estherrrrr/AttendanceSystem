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
	public JsonResult<List<Double>> showSchool(){
		List<Double> map=new ArrayList<Double>();
		map.add(97.0);map.add(93.8);
	
		JsonResult<List<Double>> list=new JsonResult<List<Double>>(map);
		return list;
	}
	
	@GetMapping("/showacademy")
	public JsonResult<List<List<Double>>> showAcademy(){
		List<Double> l1=new ArrayList<Double>();  //出勤率
		l1.add(97.0);l1.add(99.2);l1.add(78.5);
		List<Double> l2=new ArrayList<Double>();//缺勤率
		l2.add(0.4);l2.add(0.8);l2.add(0.9);
		List<Double> l3=new ArrayList<Double>();//迟到率
		l3.add(0.2);l3.add(0.6);l3.add(0.9);
		List<List<Double>> map=new ArrayList<List<Double>>();
		map.add(l1);map.add(l2);map.add(l3);
		JsonResult<List<List<Double>>> list=new JsonResult<List<List<Double>>>(map);
		return list;
	}
}
