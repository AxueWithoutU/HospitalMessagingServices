package com.capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public interface MSServiceController {
	
	@RequestMapping("/greeting")
	String greeting();
}
