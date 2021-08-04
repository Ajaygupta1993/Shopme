package com.shopme.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	private static final Logger LOGGER=LoggerFactory.getLogger(MainController.class);
	private static final String START_METHOD="method statred";
	@GetMapping("")
	public String viewHomePage() {
		 String methodName="viewHomePage";
		 LOGGER.info(methodName );
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		String methodName="viewLoginPage";
		 LOGGER.info(methodName);
		return "login";
	}
	

}
