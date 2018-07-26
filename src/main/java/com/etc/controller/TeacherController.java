package com.etc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etc.entity.Classes;
import com.etc.entity.JsonResult;
import com.etc.entity.Teacher;
import com.etc.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("**.do")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	//获取教师列表
	@GetMapping("/restteacher")
	public JsonResult<Map<String,Object>> showTeacher(int pageSize,int pageNum,
			String sort,String order,String tname,String aname){
		PageHelper.startPage(pageNum, pageSize, sort+" "+order);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("tname",tname);param.put("aname",aname);
		List<Map<String,Object>> list = teacherService.findByCondition(param);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return new JsonResult<Map<String,Object>>(pageInfo);
	}
	//删除教师
	@DeleteMapping("/restteacher")
	public JsonResult deleteClasses(@RequestBody List<Teacher> teacher){
		teacherService.doRemove(teacher);
		return new JsonResult("删除成功");
	}
	//添加教师
	@PostMapping("/restteacher")
	public JsonResult addClasses(@RequestBody Teacher teacher){
		teacherService.doAdd(teacher);
		return new JsonResult("添加成功");
	}
	//修改教师信息
	@PutMapping("/restteacher")
	public JsonResult updateClasses(@RequestBody Teacher teacher){
		teacherService.doModify(teacher);
		return new JsonResult("修改成功");
	}
	
}
