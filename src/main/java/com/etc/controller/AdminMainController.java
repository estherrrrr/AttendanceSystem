package com.etc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etc.entity.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("**.do")
public class AdminMainController {
	@GetMapping("/restchange")
	public JsonResult changePassword(@RequestParam("newPwd") String newPwd,@RequestParam("oldPwd") String oldPwd,@RequestParam("sex") String sex,@RequestParam("username") String username){
		
		return new JsonResult("修改成功！");
	}
	
}
