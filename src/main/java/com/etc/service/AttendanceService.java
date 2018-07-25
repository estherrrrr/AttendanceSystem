package com.etc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etc.dao.AttendanceMapper;
import com.etc.dao.ClassesMapper;
import com.etc.dao.CourseMapper;
import com.etc.entity.Attendance;
import com.etc.entity.AttendanceExample;
import com.etc.entity.Classes;
import com.etc.entity.ClassesExample;
import com.etc.entity.Course;
import com.etc.entity.CourseExample;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceMapper attendanceMapper;
	@Autowired
	private ClassesMapper classesMapper;
	@Autowired
	private CourseMapper courseMapper;
	
	public boolean insertAttendance(){
		Map<String,Object> param = new HashMap<String,Object>();
		try{
			String pathname="./src/main/resources/static/download/test.txt";
			File file=new File(pathname);
			InputStreamReader reader=new InputStreamReader(new FileInputStream(file));
			BufferedReader br=new BufferedReader(reader);
			String line="";
			line=br.readLine();
			while(line!=null){
				String[] str=line.split("\t");
				ClassesExample ce=new ClassesExample();
				ce.createCriteria().andMachinetagEqualTo(Integer.parseInt(str[0]));
				List<Classes> classes=classesMapper.selectByExample(ce);
				Map<String,Integer> map=new HashMap<String,Integer>();
				for(Classes c:classes){
					map.put(c.getTimetag().toString(), c.getId());
				}
				Attendance attendance=new Attendance();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date date=sf.parse(str[2]);
				attendance.setAdate(date);
				attendance.setAstatus(Integer.parseInt(str[5]));
				attendance.setCid(map.get(str[4]));
				attendance.setSnumber(str[1]);
				
				AttendanceExample ae=new AttendanceExample();
				ae.createCriteria().andSnumberEqualTo(attendance.getSnumber()).andAdateEqualTo(attendance.getAdate()).andCidEqualTo(attendance.getCid());
				List<Attendance> a=attendanceMapper.selectByExample(ae);
				if(a.size()==0&&attendance.getCid()!=null) 
					attendanceMapper.insertSelective(attendance);
				line=br.readLine();
			}
		
		}catch(Exception e){
			return false;
		}
		return true;
	}
	public Map<String,Object> attendanceDetail(int cid){
		Map<String,List<Integer>> map=new HashMap<String,List<Integer>>();
		Map<String,Object> result=new HashMap<String,Object>();
		Calendar cal = Calendar.getInstance();
		//获取当前班级
		Classes classes=classesMapper.selectByPrimaryKey(cid);
		//获取选课名单
		CourseExample ce=new CourseExample();
		ce.createCriteria().andCidEqualTo(cid);
		List<Course> course=courseMapper.selectByExample(ce);
		for(Course c:course){
			List<Integer> l=new ArrayList();
			map.put(c.getSnumber(), l);
		}
		//得到上课时间
		int weekday=classes.getTimetag()/10;
		//获取每个有课的天数
		Date today=new Date();
		Date startDay=classes.getStartdate();
		Date tempday=startDay;
		cal.setTime(startDay);
		//循环从课程开始到今天
		int count=0;//课的将节数
		while(tempday.before(today)){
			int w=cal.get(Calendar.DAY_OF_WEEK)-1;
			//如果该天有课，查询该天的签到情况
			if(w==weekday){
				for(Course c:course){
					AttendanceExample ae=new AttendanceExample();
					ae.createCriteria().andCidEqualTo(cid).andSnumberEqualTo(c.getSnumber()).andAdateEqualTo(tempday);
					List<Attendance> att=attendanceMapper.selectByExample(ae);
					if(att.size()==0)
						map.get(c.getSnumber()).add(count,0);
					else{
						if(att.get(0).getAstatus()==1)
							map.get(c.getSnumber()).add(count,1);
						else map.get(c.getSnumber()).add(count,2);
					}
				}
				count++;
			}
			cal.add(Calendar.DATE, 1);
			tempday=cal.getTime();
		}
		result.put("student", course);
		result.put("attend",map);
		result.put("count",count);
		return result;
	}

			
	public List<Attendance> findAll(){
		List<Attendance> list=attendanceMapper.selectByExample(null);
		return list;
	}
}
