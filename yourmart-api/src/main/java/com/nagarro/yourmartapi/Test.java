package com.nagarro.yourmartapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
	
	@PostMapping("/login")
	public String hello(@RequestBody String data) {
		System.out.println(data);
		return "hello there";
	}

}
