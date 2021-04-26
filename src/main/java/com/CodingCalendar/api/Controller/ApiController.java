package com.CodingCalendar.api.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.services.ContestService;
import com.CodingCalendar.api.CodeChef.CodeChefDataScrapper;



@RestController
public class ApiController {
	
	@Autowired
	private ContestService contestService;
	
	@GetMapping("/test")
//	public List<Contest> test() {
//		
//		return this.contestService.getContests();
//		
//	}
	
	public List<Contest> test() {
		
		CodeChefDataScrapper data= new CodeChefDataScrapper();
		
		return data.Data();
		
	}
	

}
