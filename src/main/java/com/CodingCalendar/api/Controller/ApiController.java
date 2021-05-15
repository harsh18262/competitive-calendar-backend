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

import com.CodingCalendar.api.DataParsers.CodeChef.CodeChef;
import com.CodingCalendar.api.DataParsers.CodeForces.CodeForces;
import com.CodingCalendar.api.DataParsers.HackerEarth.HackerEarth;
import com.CodingCalendar.api.DataParsers.HackerRank.HackerRank;
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
	public Iterable<Contest> test() 
	{
		CodeChef codechef = new CodeChef(); 
		CodeForces codeforce = new CodeForces();
		HackerEarth hackerearth =new HackerEarth();
		HackerRank hackerrank =new HackerRank();
		
		
		return contestService.getallContests();
		
	}
	
	@GetMapping("/sqltest/get")
	 public @ResponseBody Iterable<Contest> getAllcontests() 
	{
		
		return contestRepo.findAll();
	}
	@GetMapping("/sqltest/delete")
	 public @ResponseBody String deleteallcontests() 
	{
		
		 contestRepo.deleteAll();
		 return "Deleted all the entries";
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
		else if(Platform.equalsIgnoreCase("hackerearth"))
		{
			ContestList = contestService.getHackerearth();	
		}
		else if(Platform.equalsIgnoreCase("hackerrank"))
		{
			ContestList = contestService.getHackerearth();	
		}
		else if(Platform.equalsIgnoreCase("all"))
		{
			ContestList = contestService.getallContests();	
		} 
		else {
			return "Platform not found or Wrong parameter";
		}
		
		
		
		return contestService.updatetables(ContestList);
	}
		
	
	

}
