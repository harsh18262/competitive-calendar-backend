package com.CodingCalendar.api.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.entities.ContestRepository;
import com.CodingCalendar.api.services.ContestService;




@RestController
@CrossOrigin(origins = "*")
@PropertySource("classpath:application.properties")
public class ApiController {
	
	@Autowired
	private ContestService contestService;
	@Autowired
	private ContestRepository contestRepo;

	
	@GetMapping("/codechef")
	
	public List<Contest> codechef() 
	{
		
		return contestService.getCodechef();
		
	}
	
	@GetMapping("/test")
	public List<Contest> test() 
	{
		
		
		
		
		return contestService.getCodeforces();
		
	}
	
	@GetMapping("/sqltest/get")
	 public @ResponseBody Iterable<Contest> getAllcontests() 
	{
		
		return contestRepo.findAll();
	}
	
	

	@PostMapping("/sqltest/post")
	 public  String update(@RequestParam("platform") String Platform ) 
	{
		Iterable <Contest> ContestList; 
		ContestList = null;
		
		if(Platform.equalsIgnoreCase("codechef"))
		{
		ContestList = contestService.getCodechef();
		}
		else if(Platform.equalsIgnoreCase("codeforces"))
		{
			ContestList = contestService.getCodeforces();	
		}
		else
		{
			return "Platform not found or Wrong parameter";
		}
		
		
		
		return contestService.updatetables(ContestList);
	}
		
	
	

}
