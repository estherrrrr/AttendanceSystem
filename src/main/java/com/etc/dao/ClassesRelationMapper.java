package com.etc.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ClassesRelationMapper {
	List<Map<String,Object>> selectClasses(Map<String,Object> param);
}