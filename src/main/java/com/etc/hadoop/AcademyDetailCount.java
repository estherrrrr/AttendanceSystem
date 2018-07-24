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
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class AcademyDetailCount {
	static  String INPUT_PATH="hdfs://classes14:9000/JJ/input/";
	static  String OUTPUT_PATH="hdfs://classes14:9000/JJ/output/";
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
		Job job=Job.getInstance(conf,"SchoolAttendanceCount");
		FileInputFormat.setInputPaths(job, INPUT_PATH+filename);
		FileOutputFormat.setOutputPath(job, outpath);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//job.setPartitionerClass(MyPartitioner.class);
		//job.setNumReduceTasks(4);
		job.waitForCompletion(true);
		readFromServer();
	}
	private static void readFromServer() throws IOException{
		FileSystem fs = FileSystem.get(uri,new Configuration());
		FSDataInputStream fsdis = fs.open(new Path("/JJ/output/part-r-00000"));
		FileOutputStream fos = new FileOutputStream("./src/main/resources/static/download/attendanceDetailResult.txt");
		IOUtils.copyBytes(fsdis, fos, 1024, true);
		IOUtils.closeStream(fsdis);
		fs.close();
	}
	private static void putFileToServer(String filename) throws IOException{
		FileSystem fs = FileSystem.get(uri,new Configuration());
		
		
		Path lSrc = new Path("./src/main/resources/static/download/"+filename);
		Path dSrc = new Path("/JJ/input/");
		fs.copyFromLocalFile(lSrc, dSrc);
		fs.close();
	}
	
	static class MyMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
		
		@Override
		protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			
			String[] splits = v1.toString().split("\t");
			
			
			context.write(new Text(splits[3]+"\t"+splits[1]+"\t"+splits[2]),new LongWritable(1));
			}
		}
		

	static class MyReducer extends Reducer<Text,LongWritable,Text,LongWritable>{

		@Override
		protected void reduce(Text k2, Iterable<LongWritable> v2s,
				Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
			long num=0;
			for(LongWritable a :v2s){
				num += a.get();
			}
			context.write(k2, new LongWritable(num));
		}
		
	}
	
}

