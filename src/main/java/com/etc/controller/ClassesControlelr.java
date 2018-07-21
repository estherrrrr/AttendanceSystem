package com.etc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
		System.out.println("进入查询方法");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("cname",cname);param.put("tname",tname);param.put("aname",aname);
		List<Map<String,Object>> list = classesService.findByCondition(param);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return new JsonResult<Map<String,Object>>(pageInfo);
	}
	
	@DeleteMapping("/restclasses")
	public JsonResult deleteClasses(@RequestBody List<Classes> classes){
		classesService.doRemove(classes);
		return new JsonResult("删除成功");
	}

}
