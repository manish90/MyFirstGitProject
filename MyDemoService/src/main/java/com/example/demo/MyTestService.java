package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class MyTestService {
	@RequestMapping("/MyService" )
	public String getMessage() {
		return "Hello Service";
	}
}
