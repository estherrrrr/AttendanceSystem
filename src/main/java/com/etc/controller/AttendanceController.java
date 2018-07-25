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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etc.entity.Academy;
import com.etc.entity.Attendance;
import com.etc.entity.Classes;
import com.etc.entity.JsonResult;
import com.etc.entity.Student;
import com.etc.entity.Teacher;
import com.etc.hadoop.AcademyCount;
import com.etc.hadoop.AcademyDetailCount;
import com.etc.hadoop.SchoolCount;
import com.etc.hadoop.StudentEverydayCount;
import com.etc.hadoop.TopClassesCount;
import com.etc.service.AcademyService;
import com.etc.service.AttendanceService;
import com.etc.service.ClassesService;
import com.google.common.collect.Multiset.Entry;

@RestController
@RequestMapping("**.do")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private ClassesService classesService;
	@Autowired
	private AcademyService academyService;
	
	private List<Integer> classesid=null;//该学院所有课程id
	
	@GetMapping("changepsw")
	public JsonResult<List<String>> changePsw(){
		List<String> list = new LinkedList<String>();
		list.add("success");
		return new JsonResult(list);
	}
	@GetMapping("/{id}/showtable")
	public JsonResult<Map<String,Object>> getClassTable(@PathVariable int id) throws IOException{
		return new JsonResult(attendanceService.attendanceDetail(id));
	}
	@GetMapping("/showschool")
	public JsonResult<List<Map<String,List<Double>>>> showSchool() throws IOException, ClassNotFoundException, URISyntaxException, InterruptedException{
		/*List<Attendance> attendances=attendanceService.findAll();
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
         
         SchoolCount.test("attendance.txt");*/
       
         
		Calendar c = Calendar.getInstance();
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
		int COUNT = 300;
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
						l.add(COUNT-latePeoplo-attendentPeople);
						double rate = (attendentPeople+latePeoplo)/COUNT*100;
						rate = (double)Math.round(rate*100)/100;
						l.add(rate);
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
						l.add(COUNT-latePeoplo-attendentPeople);
						double rate = (attendentPeople+latePeoplo)/COUNT*100;
						rate = (double)Math.round(rate*100)/100;
						l.add(rate);
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
						l.add(COUNT-latePeoplo-attendentPeople);
						double rate = (attendentPeople+latePeoplo)/COUNT*100;
						rate = (double)Math.round(rate*100)/100;
						l.add(rate);
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
						l.add(COUNT-latePeoplo-attendentPeople);
						double rate = (attendentPeople+latePeoplo)/COUNT*100;
						rate = (double)Math.round(rate*100)/100;
						l.add(rate);
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
	public JsonResult<List<Map<String,Double>>> showAcademy() throws ClassNotFoundException, IOException, URISyntaxException, InterruptedException{
		
		//AcademyCount.test("attendance.txt");
		
		List<Academy> academies=academyService.findAll();//所有学院
		Map<String,List<Integer>> classes=new HashMap<String,List<Integer>>();//每个学院其下的所有课程
		Map<String,Double> attendance=new HashMap<String,Double>();//所有学院出勤数
		Map<String,Double> late=new HashMap<String,Double>();//所有学院迟到数
		Map<String,Integer> classesnum=new HashMap<String,Integer>();//学院已经上过的课程数
		for(Academy academy:academies){
			List<Integer> classesid=classesService.findByAid(academy.getId());
			classes.put(academy.getAname(), classesid);
			attendance.put(academy.getAname(), 0.0);
			late.put(academy.getAname(), 0.0);
			classesnum.put(academy.getAname(), 0);
		}
		
		File file=new File("./src/main/resources/static/download/academyResult.txt");
	    BufferedReader reader=null;
		String temp=null;
		int tid=0;
		int oldclass=0;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				String[] info=temp.split("\t");
				tid=Integer.parseInt(info[0]);
				for(Academy academy:academies){
					
					if(classes.get(academy.getAname()).contains(tid)){
						oldclass=classesnum.get(academy.getAname())+1;
						classesnum.put(academy.getAname(), oldclass);
						
						if(info[1].equals("1")){//该学院所有未迟到的人数
							Double oldnum=attendance.get(academy.getAname());
							oldnum+=Double.parseDouble(info[2]);
							attendance.put(academy.getAname(),oldnum);
						}else{//该学院所有迟到的人数
							Double oldnum=late.get(academy.getAname());
							oldnum+=Double.parseDouble(info[2]);
							late.put(academy.getAname(),oldnum);
						}break;	
					}
						
				}
			}
		}catch(Exception e){e.printStackTrace();}
		reader.close();
		
		Map<String,Double> unattendents=new HashMap<String,Double>();
		for(Academy academy:academies){
			String aname=academy.getAname();
			Double attnum=attendance.get(aname);
			Double latenum=late.get(aname);
			int total=classesnum.get(aname)*30*4;
			
			double rate = (attnum+latenum)/total*100;
			rate = (double)Math.round(rate*100)/100;
			attendance.put(aname, rate);
			
			rate = latenum/total*100;
			rate = (double)Math.round(rate*100)/100;
			late.put(aname, rate);
			
			unattendents.put(aname, 100-attendance.get(aname));
		}
		List<Map<String,Double>> result=new ArrayList<Map<String,Double>>();
		result.add(attendance);result.add(unattendents);result.add(late);
		return new JsonResult<List<Map<String,Double>>>(result);
	}
	
	
	
	@GetMapping("/showacademydetail")
	public JsonResult<List<Double>> showAcademyDetail(HttpServletRequest request) throws ClassNotFoundException, IOException, URISyntaxException, InterruptedException{
		
		String  aname=(String) request.getSession().getAttribute("academy"); 
		
		Academy academy=academyService.findByAname(aname);
		classesid=classesService.findByAid(academy.getId());//该学院所有课程id
		
		//AcademyDetailCount.test("attendance.txt");
		//TopClassesCount.test("attendance.txt");
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		long today=c.getTimeInMillis();
		
		Map<String,Double> att=new HashMap<String,Double>();
		att.put("Mon", 0.0);att.put("Tue", 0.0);att.put("Wed", 0.0);att.put("Thu", 0.0);att.put("Fri", 0.0);
		Map<String,Integer> classesnum=new HashMap<String,Integer>();//某天已经上过的课程数
		
		
		File file=new File("./src/main/resources/static/download/attendanceDetailResult.txt");
	    BufferedReader reader=null;
		String temp=null;
		long day=0;
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
				c.set(2018,month,date);
		        long newday=c.getTimeInMillis();  
		        day=(newday-today)/(1000*60*60*24);
		        
		        //只留一周内
		        if(day<7){
		        	
		        	//属于该学院的课程
		        	if(classesid.contains(Integer.parseInt(info[1]))) {
		        		
						att.put(time[0],att.get(time[0])+Double.parseDouble( info[2]));
						
						if(!classesnum.containsKey(time[0]))
							classesnum.put(time[0], 1);
						else
							classesnum.put(time[0], classesnum.get(time[0])+1);
					}
		        }
				
			}
		}catch(Exception e){e.printStackTrace();}
		reader.close();
		
		List<Double> map=new ArrayList<Double>();
		if(classesnum.containsKey("Mon"))map.add(att.get("Mon")/(classesnum.get("Mon")*30)*100);
		else map.add(0.0);
		if(classesnum.containsKey("Tue"))map.add(att.get("Tue")/(classesnum.get("Tue")*30)*100);
		else map.add(0.0);
		if(classesnum.containsKey("Wed"))map.add(att.get("Wed")/(classesnum.get("Wed")*30)*100);
		else map.add(0.0);
		if(classesnum.containsKey("Thu"))map.add(att.get("Thu")/(classesnum.get("Thu")*30)*100);
		else map.add(0.0);
		if(classesnum.containsKey("Fri"))map.add(att.get("Fri")/(classesnum.get("Fri")*30)*100);
		else map.add(0.0);
		JsonResult<List<Double>> list=new JsonResult<List<Double>>(map);
		return list;
		
	}
	
	@GetMapping("/showtopclass")
	public JsonResult<List<Map<String,Object>>> showTopClass(HttpServletRequest request) throws IOException, ClassNotFoundException, URISyntaxException, InterruptedException{
		
		try { 
			Thread.sleep(500); 
			} catch (InterruptedException e) { 
			e.printStackTrace(); 
			}
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();//课程名-出勤率
		
		File file=new File("./src/main/resources/static/download/topClassesResult.txt");
	    BufferedReader reader=null;
		String temp=null;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				String[] info=temp.split("\t");
				//属于该学院的课程
				if(classesid.contains(Integer.parseInt(info[0]))) {
					
					Classes classes=classesService.findById(Integer.parseInt(info[0]));
					
					Map<String,Object> map1=new HashMap<String,Object>();
					double rate = Double.parseDouble(info[1])/(4*30)*100;
					rate = (double)Math.round(rate*100)/100;
					map1.put("name", classes.getCname());map1.put("num", rate);
					list.add(map1);
				}
			}
		}catch(Exception e){e.printStackTrace();}
		reader.close();
		
		Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double name1 = Double.valueOf(o1.get("num").toString());//name1是从你list里面拿出来的一个 
                Double name2 = Double.valueOf(o2.get("num").toString()) ; //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }
        });
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	@GetMapping("/showteacherclasses")
	public JsonResult<List<Map<String,Object>>> showTeacherClasses(HttpServletRequest request) throws IOException{
		Teacher teacher=(Teacher) request.getSession().getAttribute("admin");
		
		classesid=classesService.findByTid(teacher.getId());//教师旗下所有课程
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();//课程名-出勤率
		
		File file=new File("./src/main/resources/static/download/topClassesResult.txt");
	    BufferedReader reader=null;
		String temp=null;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				String[] info=temp.split("\t");
				if(classesid.contains(Integer.parseInt(info[0]))){
					Classes classes=classesService.findById(Integer.parseInt(info[0]));
					
					Map<String,Object> map1=new HashMap<String,Object>();
					map1.put("classname", classes.getCname());map1.put("num", Double.parseDouble(info[1])/(4*30)*100);
					list.add(map1);
				}
			}
		}catch(Exception e){e.printStackTrace();}
		reader.close();
		
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	@GetMapping("/showstudentattendance")
	public JsonResult<Map<String,Integer>> showStudentAttendance(HttpServletRequest request) throws IOException, ClassNotFoundException, URISyntaxException, InterruptedException, ParseException{
		Student student=(Student) request.getSession().getAttribute("admin");
		
		StudentEverydayCount.test("attendance.txt");
		
		Map<String,Integer> map=new TreeMap<String,Integer>();
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dayAfter=null;
		c.set(2018,5,25); 
		dayAfter=format.format(c.getTime());
		for(int i=0,day=0;i<33;i++){
			
			map.put(dayAfter,0);
			
			day=c.get(Calendar.DATE); 
			c.set(Calendar.DATE,day+1); 
			dayAfter=format.format(c.getTime());
		}
		
		
		File file=new File("./src/main/resources/static/download/studentEverydayResult.txt");
	    BufferedReader reader=null;
		String temp=null;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				String[] info=temp.split("\t");
				if(student.getSnumber().equals(info[0])){
					
					String[] time=info[1].split(" ");
					int month=1;
					switch (time[1]){
					case "Jul":month=7;break;
					case "Jun":month=6;break;
					}
					int date=Integer.parseInt(time[2]);
					c.set(2018,month-1,date);
					dayAfter= format.format(c.getTime());
					
					map.put(dayAfter, Integer.parseInt(info[2]));
					
				}
				
		        
			}
		}catch(Exception e){e.printStackTrace();}
		reader.close();
		
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
        	@Override
            //升序排序
            public int compare(java.util.Map.Entry<String, Integer> o1,
            		java.util.Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }

            
        });
		
		return new JsonResult<Map<String,Integer>>(map);
	}
}
