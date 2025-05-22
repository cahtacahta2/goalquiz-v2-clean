package com.example.helloSpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String hello() {
		return "Merhaba Spring Boot!";
	}

	@GetMapping("/sss")
	public String hellos() {
		return "Merhaba Spring Bootssssssssssssss!";
	}
}