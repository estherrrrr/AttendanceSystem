package com.etc.controller;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

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
	
	
	
	@GetMapping("/showacademydetail")
	public JsonResult<List<Double>> showAcademyDetail(HttpServletRequest request){
		
		String academy =(String) request.getSession().getAttribute("academy"); 
		
		
		List<Double> map=new ArrayList<Double>();
		map.add(97.0);map.add(93.8);
		map.add(87.0);map.add(100.0);
		JsonResult<List<Double>> list=new JsonResult<List<Double>>(map);
		return list;
		
	}
	
	@GetMapping("/showtopclass")
	public JsonResult<List<Map<String,Object>>> showTopClass(){
		
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("name", "社交");map1.put("num", 79.2);
		Map<String,Object> map2=new HashMap<String,Object>();
		map2.put("name", "计网");map2.put("num", 88.2);
		Map<String,Object> map3=new HashMap<String,Object>();
		map3.put("name", "计组");map3.put("num", 89.2);
		Map<String,Object> map4=new HashMap<String,Object>();
		map4.put("name", "面向对象");map4.put("num", 93.2);
		Map<String,Object> map5=new HashMap<String,Object>();
		map5.put("name", "数据库");map5.put("num", 97.2);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list.add(map1);list.add(map2);list.add(map3);list.add(map4);list.add(map5);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
}
