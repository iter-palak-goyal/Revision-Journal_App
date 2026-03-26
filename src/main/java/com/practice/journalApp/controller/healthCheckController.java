package com.practice.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheckController {
	//yeh function checkHelth jo h yeh map ho jayega heath-check naam k endpoint se
	
	@GetMapping("/health-check")
	public String checkHealth() {
		return "Ok";
	}
}
