package com.etc.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etc.entity.Classes;
import com.etc.entity.JsonResult;
import com.etc.service.ClassesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("**.do")
public class ClassesControlelr {
	@Autowired
	private ClassesService classesService;
	
	@GetMapping("/restclasses")
	public JsonResult<Map<String,Object>> showClasses(int pageSize,int pageNum,
			String sort,String order,String cname,String tname,String aname){
		PageHelper.startPage(pageNum, pageSize, sort+" "+order);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("cname",cname);param.put("tname",tname);param.put("aname",aname);param.put("tnumber",0);
		List<Map<String,Object>> list = classesService.findByCondition(param);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return new JsonResult<Map<String,Object>>(pageInfo);
	}
	@GetMapping("/{id}/restclasses")
	public JsonResult<Map<String,Object>> showMyClasses(@PathVariable int id,int pageSize,int pageNum,
			String sort,String order){
		PageHelper.startPage(pageNum, pageSize, sort+" "+order);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("tnumber",id);
		List<Map<String,Object>> list = classesService.findByCondition(param);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return new JsonResult<Map<String,Object>>(pageInfo);
	}
	
	@DeleteMapping("/restclasses")
	public JsonResult deleteClasses(@RequestBody List<Classes> classes){
		classesService.doRemove(classes);
		return new JsonResult("删除成功");
	}
	
	@PostMapping("/restclasses")
	public JsonResult addClasses(@RequestBody Classes classes){
		classesService.doAdd(classes);
		return new JsonResult("添加成功");
	}
	
	@PutMapping("/restclasses")
	public JsonResult updateClasses(@RequestBody Classes classes){
		classesService.doModify(classes);
		return new JsonResult("修改成功");
	}

}
