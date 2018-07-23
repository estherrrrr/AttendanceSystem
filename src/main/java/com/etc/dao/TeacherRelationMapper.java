package com.etc.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeacherRelationMapper {
	List<Map<String,Object>> selectTeacher(Map<String,Object> param);
}