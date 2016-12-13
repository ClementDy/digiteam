package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.HomeClement;
import com.example.controller.Home;

@SpringBootApplication
public class MainClement {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HomeClement.class, args);
	}
}
