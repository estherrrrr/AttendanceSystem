package com.etc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etc.entity.JsonResult;
import com.etc.hadoop.AttendanceCount;

@RestController
@RequestMapping("**.do")
public class FileUploadController {
	
	@PostMapping("/restupload")
	 public @ResponseBody JsonResult uploadImg(@RequestParam("file") MultipartFile file) throws ClassNotFoundException, IOException, URISyntaxException, InterruptedException {
			String contentType = file.getContentType();
	        String fileName = file.getOriginalFilename();
	        /*System.out.println("fileName-->" + fileName);
	        System.out.println("getContentType-->" + contentType);*/
	        String filePath = "./src/main/resources/static/download/";
	        try {
	            uploadFile(file.getBytes(), filePath, fileName);
	            
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        AttendanceCount ac=new AttendanceCount();
	        ac.test(fileName);
	        
	        return new JsonResult("uploadimg success");
	    }
	 @PostMapping("/{id}/restupload")
	 public @ResponseBody JsonResult uploadStudent(@RequestParam("file") MultipartFile file) {
		    System.out.println(1111111);
	        String contentType = file.getContentType();
	        String fileName = file.getOriginalFilename();
	        /*System.out.println("fileName-->" + fileName);
	        System.out.println("getContentType-->" + contentType);*/
	        String filePath = "./src/main/resources/static/download/";
	        try {
	            uploadFile(file.getBytes(), filePath, fileName);
	            
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        //返回json
	        return new JsonResult("uploadimg success");
	    }
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();     
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
