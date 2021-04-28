package com.CodingCalendar.api.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.services.ContestService;
import com.CodingCalendar.api.CodeChef.CodeChefDataScrapper;
import com.CodingCalendar.api.CodeForces.CodeForcesDataScrapper;



@RestController
public class ApiController {
	
	@Autowired
	private ContestService contestService;
	
	@GetMapping("/codechef")
	
	public List<Contest> codechef() {
		
		CodeChefDataScrapper Codechef= new CodeChefDataScrapper();
		
		return Codechef.Data();
		
	}
	
	@GetMapping("/test")
	public String test() {
		CodeForcesDataScrapper CodeForces =new CodeForcesDataScrapper();
		
		String url=CodeForces.Data();
		
		return url;
		
	}
	
	

}
