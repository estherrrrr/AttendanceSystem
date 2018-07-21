package com.etc.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etc.entity.JsonResult;

@RestController
@RequestMapping("**.do")
public class AttendanceManagementController {
	
	@PostMapping("/restupload")
	 public @ResponseBody JsonResult uploadImg(@RequestParam("file") MultipartFile file) {
	        String contentType = file.getContentType();
	        String fileName = file.getOriginalFilename();
	        /*System.out.println("fileName-->" + fileName);
	        System.out.println("getContentType-->" + contentType);*/
	        String filePath = "D:\\";
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
