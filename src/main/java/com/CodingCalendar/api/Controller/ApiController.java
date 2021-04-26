package com.CodingCalendar.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {
	@GetMapping("/test")
	public String test() {
		
		return "Test Page";
		
	}
	

}
