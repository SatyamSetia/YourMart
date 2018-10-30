package com.nagarro.yourmartapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication 
@ComponentScan("com.nagarro.yourmartapi") 
public class YourmartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourmartApiApplication.class, args);
	}
}
