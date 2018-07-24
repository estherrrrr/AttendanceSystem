package com.etc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etc.entity.Academy;
import com.etc.entity.Attendance;
import com.etc.entity.JsonResult;
import com.etc.hadoop.AcademyCount;
import com.etc.hadoop.SchoolCount;
import com.etc.service.AcademyService;
import com.etc.service.AttendanceService;
import com.etc.service.ClassesService;

@RestController
@RequestMapping("**.do")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private ClassesService classesService;
	@Autowired
	private AcademyService academyService;
	
	@GetMapping("/showschool")
	public JsonResult<List<Map<String,List<Double>>>> showSchool() throws IOException, ClassNotFoundException, URISyntaxException, InterruptedException{
		List<Attendance> attendances=attendanceService.findAll();
		FileOutputStream fos = new FileOutputStream("./src/main/resources/static/download/attendance.txt");
		OutputStreamWriter writer = new OutputStreamWriter(fos);
		for(Attendance a:attendances){
			writer.write(a.getSnumber() + "\t");
			 writer.write(String.valueOf(a.getCid()) + "\t");
			 writer.write(String.valueOf(a.getAstatus()) + "\t");
             writer.write(a.getAdate()+"\r\n");
		}
		 writer.flush();
         writer.close();
         
         SchoolCount.test("attendance.txt");
       
         
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		c.setTime(new Date());
		long today=c.getTimeInMillis();
        
		List<Map<String,List<Double>>> list=new ArrayList<Map<String,List<Double>>>();
		Map<String,List<Double>> week1=new HashMap<String,List<Double>>();
		Map<String,List<Double>> week2=new HashMap<String,List<Double>>();
		Map<String,List<Double>> week3=new HashMap<String,List<Double>>();
		Map<String,List<Double>> week4=new HashMap<String,List<Double>>();
		
        File file=new File("./src/main/resources/static/download/attendanceResult.txt");
		BufferedReader reader=null;
		String temp=null;
		long day=0;
		Double attendentPeople=0.0;
		Double latePeoplo=0.0;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				String[] info=temp.split("\t");
				String[] time=info[0].split(" ");
				int month=1;
				switch (time[1]){
				case "Jul":month=7;break;
				case "Jun":month=6;break;
				}
				int date=Integer.parseInt(time[2]);
				c.set(2018,month,date);//这里与真实的月份之间相差1  
		        long newday=c.getTimeInMillis();  
		        day=(newday-today)/(1000*60*60*24);
				
				if(day<7){
					if(info[1].equals("1")){
						List<Double> l=new ArrayList<Double>();
						attendentPeople=Double.parseDouble(info[2]);
						l.add(attendentPeople);
						week4.put(time[0], l);
					}else{
						List<Double> l=week4.get(time[0]);
						latePeoplo=Double.parseDouble(info[2]);
						l.add(latePeoplo);
						l.add(250-latePeoplo-attendentPeople);
						l.add((attendentPeople+latePeoplo)/250*100);
						week4.put(time[0], l);
					}	
				}
				else if(day<14){
					if(info[1].equals("1")){
						List<Double> l=new ArrayList<Double>();
						attendentPeople=Double.parseDouble(info[2]);
						l.add(attendentPeople);
						week3.put(time[0], l);
					}else{
						List<Double> l=week3.get(time[0]);
						latePeoplo=Double.parseDouble(info[2]);
						l.add(latePeoplo);
						l.add(250-latePeoplo-attendentPeople);
						l.add((attendentPeople+latePeoplo)/250*100);
						week3.put(time[0], l);
					}	
				}else if(day<21){
					if(info[1].equals("1")){
						List<Double> l=new ArrayList<Double>();
						attendentPeople=Double.parseDouble(info[2]);
						l.add(attendentPeople);
						week2.put(time[0], l);
					}else{
						List<Double> l=week2.get(time[0]);
						latePeoplo=Double.parseDouble(info[2]);
						l.add(latePeoplo);
						l.add(250-latePeoplo-attendentPeople);
						l.add((attendentPeople+latePeoplo)/250*100);
						week2.put(time[0], l);
					}	
				}else if(day<28){
					if(info[1].equals("1")){
						List<Double> l=new ArrayList<Double>();
						attendentPeople=Double.parseDouble(info[2]);
						l.add(attendentPeople);
						week1.put(time[0], l);
					}else{
						List<Double> l=week1.get(time[0]);
						latePeoplo=Double.parseDouble(info[2]);
						l.add(latePeoplo);
						l.add(250-latePeoplo-attendentPeople);
						l.add((attendentPeople+latePeoplo)/250*100);
						week1.put(time[0], l);
					}	
				}
					
				
			}
	     }catch(Exception e){e.printStackTrace();}
		reader.close();
		list.add(week1);list.add(week2);list.add(week3);list.add(week4);
		
		//判断是否有空的天数
		for(Map<String,List<Double>> week:list){
			if(week.size()!=4){
				List<Double> l=new ArrayList<Double>();
				l.add(0.0);l.add(0.0);l.add(0.0);l.add(0.0);
				if(!week.containsKey("Mon")){
					week.put("Mon", l);
				}if(!week.containsKey("Tue")){
					week.put("Tue", l);
				}if(!week.containsKey("Wed")){
					week.put("Wed", l);
				}if(!week.containsKey("Thu")){
					week.put("Thu", l);
				}if(!week.containsKey("Fri")){
					week.put("Fri", l);
				}
			}
		}
		
		

		return new JsonResult<List<Map<String,List<Double>>>>(list);
	}
	
	@GetMapping("/showacademy")
	public JsonResult<List<List<Double>>> showAcademy() throws ClassNotFoundException, IOException, URISyntaxException, InterruptedException{
		
		//AcademyCount.test("attendance.txt");
		
		List<Academy> academies=academyService.findAll();
		Map<String,List<Integer>> classes=new HashMap<String,List<Integer>>();
		Map<String,Double> attendance=new HashMap<String,Double>();
		Map<String,Double> late=new HashMap<String,Double>();
		for(Academy academy:academies){
			List<Integer> classesid=classesService.findByAid(academy.getId());
			classes.put(academy.getAname(), classesid);
			attendance.put(academy.getAname(), 0.0);
			late.put(academy.getAname(), 0.0);
		}
		
		File file=new File("./src/main/resources/static/download/academyResult.txt");
	    BufferedReader reader=null;
		String temp=null;
		int tid=0;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				String[] info=temp.split("\t");
				tid=Integer.parseInt(info[0]);
				for(Academy academy:academies){
					if(classes.get(academy.getAname()).contains(tid)){
						if(info[1].equals("1")){
							Double oldnum=attendance.get(academy.getAname());
							oldnum+=Double.parseDouble(info[2]);
							attendance.put(academy.getAname(),oldnum);
						}else{
							Double oldnum=late.get(academy.getAname());
							oldnum+=Double.parseDouble(info[2]);
							late.put(academy.getAname(),oldnum);
						}
							
					}
						
				}
			}
		}catch(Exception e){e.printStackTrace();}
		reader.close();
		
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
