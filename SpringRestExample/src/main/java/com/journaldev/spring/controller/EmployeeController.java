package com.journaldev.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class EmployeeController {
 
	@Value("${name}")
	String name;
	
	@RequestMapping(value="/testMyService" , method = RequestMethod.GET)
	public @ResponseBody String testService() {
		System.out.println("value : "+name);
		return "hello service test : " +name;
		
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download() throws IOException {
        String fileName= "C:/Users/m.kumar.thakur/VideoFile/fish.mp4";
            File file = new File(fileName);
            InputStreamResource res = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
		/*
		 * return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
		 * .contentType(MediaTypeFactory .getMediaType(res)
		 * .orElse(MediaType.APPLICATION_OCTET_STREAM)) .body(res);
		 */
		
		  ResponseEntity<InputStreamResource> response = ResponseEntity.ok().headers(headers)
		  .contentLength(file.length())
		  .contentType(MediaType.parseMediaType("application/txt")).body(res);
		  
		  return response;
		 
    }
	
}
