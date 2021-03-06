package com.etc.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etc.entity.JsonResult;
import com.etc.hadoop.AttendanceCount;
import com.etc.service.AttendanceService;
import com.etc.service.FileService;

@RestController
@RequestMapping("**.do")
public class FileUploadController {
	private String fileName;
	@Autowired
	private FileService fileService;
	@Autowired
	private AttendanceService attendanceService;
	
	@GetMapping("/restinsert")
 	public @ResponseBody JsonResult insertAtt() throws ClassNotFoundException, IOException, URISyntaxException, InterruptedException{
		AttendanceCount ac=new AttendanceCount();
        ac.test(fileName);
		attendanceService.insertAttendance();
		return new JsonResult("导入成功");
	 }
	@PostMapping("/restupload")
	 public @ResponseBody JsonResult uploadImg(@RequestParam("file") MultipartFile file) throws ClassNotFoundException, IOException, URISyntaxException, InterruptedException {
	        String contentType = file.getContentType();
	        this.fileName = file.getOriginalFilename();
	        /*System.out.println("fileName-->" + fileName);
	        System.out.println("getContentType-->" + contentType);*/
	        String filePath = "./src/main/resources/static/download/";
	        try {
	            fileService.uploadFile(file.getBytes(), filePath, fileName);
	            
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return new JsonResult("文件上传成功！");
	    }
	 @PostMapping("/{id}/restupload")
	 public @ResponseBody JsonResult uploadStudent(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) throws IOException{
	        String contentType = file.getContentType();
	        fileName = file.getOriginalFilename();
	        //System.out.println("fileName-->" + fileName);
	        //System.out.println("getContentType-->" + contentType);
	        String filePath = "./src/main/resources/static/download/";
	        try {
	            fileService.uploadFile(file.getBytes(), filePath, fileName);
	            fileService.fileRead(fileName,id);
	            
	        } catch (Exception e) {
	        	return new JsonResult("上传失败");
	        }
	        //返回json
	        return new JsonResult("上传成功");
	    
	 }
	
}
