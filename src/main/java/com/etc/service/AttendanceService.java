package com.etc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.AttendanceMapper;
import com.etc.entity.Attendance;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceMapper attendanceMapper;
	
	public List<Attendance> findAll(){
		List<Attendance> list=attendanceMapper.selectByExample(null);
		return list;
	}
}
