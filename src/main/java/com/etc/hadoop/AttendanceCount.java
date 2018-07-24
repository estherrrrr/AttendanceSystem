package com.etc.hadoop;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class AttendanceCount {
	static  String INPUT_PATH="hdfs://classes14:9000/input/";
	static  String OUTPUT_PATH="hdfs://classes14:9000/output";
	private static URI uri = null;
	
	static{
		try {
			uri = new URI("hdfs://classes14:9000");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static void test(String filename) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException{
		Configuration conf=new Configuration();
		Path outpath = new Path(OUTPUT_PATH);
		putFileToServer(filename);
		FileSystem fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
		if(fs.exists(outpath)){
			fs.delete(outpath,true);
		}
		
		//��ʼ��ҵ
		Job job=Job.getInstance(conf,"AttendanceCount");
		FileInputFormat.setInputPaths(job, INPUT_PATH+filename);
		FileOutputFormat.setOutputPath(job, outpath);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(AttendanceWritable.class);
		
		//job.setPartitionerClass(MyPartitioner.class);
		//job.setNumReduceTasks(4);
		job.waitForCompletion(true);
		readFromServer();
	}
	private static void readFromServer() throws IOException{
		FileSystem fs = FileSystem.get(uri,new Configuration());
		FSDataInputStream fsdis = fs.open(new Path("/output/part-r-00000"));
		FileOutputStream fos = new FileOutputStream("./src/main/resources/static/download/test.txt");
		IOUtils.copyBytes(fsdis, fos, 1024, true);
		IOUtils.closeStream(fsdis);
		fs.close();
	}
	private static void putFileToServer(String filename) throws IOException{
		FileSystem fs = FileSystem.get(uri,new Configuration());
		
		
		Path lSrc = new Path("./src/main/resources/static/download/"+filename);
		Path dSrc = new Path("/input/");
		fs.copyFromLocalFile(lSrc, dSrc);
		fs.close();
	}
	
	static class MyMapper extends Mapper<LongWritable,Text,Text,AttendanceWritable>{
		
		@Override
		protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, Text, AttendanceWritable>.Context context)
				throws IOException, InterruptedException {
			
			String[] splits = v1.toString().split(" ");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String time=splits[3];
			String[] timeStamp = time.split(":");
			int hour=Integer.parseInt(timeStamp[0]);
			int minute=Integer.parseInt(timeStamp[1]);
			int seconds=Integer.parseInt(timeStamp[2]);
			int status=0;
			int w2=0;
			int w1=0;
			try {
				Date date=sf.parse(splits[2]);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				w1=cal.get(Calendar.DAY_OF_WEEK)-1;
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(get_time(hour,minute,seconds)>=get_time(7,40,0)&&get_time(hour,minute,seconds)<=get_time(8,5,0)){
				status=1;
				w2=1;	
			}
			else if(get_time(hour,minute,seconds)>=get_time(8,6,0)&&get_time(hour,minute,seconds)<=get_time(9,40,0)){
				status=2;
				w2=1;	
			}
			else if(get_time(hour,minute,seconds)>=get_time(9,50,0)&&get_time(hour,minute,seconds)<=get_time(10,15,0)){
				status=1;
				w2=2;	
			}
			else if(get_time(hour,minute,seconds)>=get_time(10,16,0)&&get_time(hour,minute,seconds)<=get_time(11,50,0)){
				status=2;
				w2=2;	
			}
			else if(get_time(hour,minute,seconds)>=get_time(14,10,0)&&get_time(hour,minute,seconds)<=get_time(14,35,0)){
				status=1;
				w2=3;	
			}
			else if(get_time(hour,minute,seconds)>=get_time(14,36,0)&&get_time(hour,minute,seconds)<=get_time(16,10,0)){
				status=2;
				w2=3;	
			}
			else if(get_time(hour,minute,seconds)>=get_time(16,20,0)&&get_time(hour,minute,seconds)<=get_time(16,45,0)){
				status=1;
				w2=4;	
			}
			else if(get_time(hour,minute,seconds)>=get_time(16,46,0)&&get_time(hour,minute,seconds)<=get_time(18,20,0)){
				status=2;
				w2=4;	
			}
			context.write(new Text(splits[0]+"\t"+splits[1]+"\t"+splits[2]+"\t"+splits[3]),new AttendanceWritable(w1*10+w2,status));
			}
		}
		
	static int get_time(int h,int m,int s){
	    return h*60*60 + m*60 + s;
	}

	static class MyReducer extends Reducer<Text,AttendanceWritable,Text,AttendanceWritable>{

		@Override
		protected void reduce(Text k2, Iterable<AttendanceWritable> v2s,
				Reducer<Text, AttendanceWritable, Text, AttendanceWritable>.Context context) throws IOException, InterruptedException {
			AttendanceWritable aw=new AttendanceWritable();
			for(AttendanceWritable a :v2s){
				aw.setAstatus(a.getAstatus());
				aw.setCstatus(a.getCstatus());
			}
			context.write(k2, aw);
		}
		
	}
	
}

