package com.CodingCalendar.api.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.entities.ContestRepository;
import com.CodingCalendar.api.services.ContestService;
import com.CodingCalendar.api.CodeChef.CodeChefDataScrapper;
import com.CodingCalendar.api.CodeForces.CodeForcesDataScrapper;



@RestController
@CrossOrigin(origins = "*")
public class ApiController {
	
	@Autowired
	private ContestService contestService;
	@Autowired
	private ContestRepository contestRepo;
	
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
	@GetMapping("/sqltest/get")
	 public @ResponseBody Iterable<Contest> getAllcontests() 
	{
		
		return contestRepo.findAll();
	}
	int f=0;
	@GetMapping("/sqltest/post")
	 public @ResponseBody String updatecontests() 
	{
		CodeChefDataScrapper Codechef= new CodeChefDataScrapper();
		Iterable < Contest > ContestList , data;
		//ContestList = new ArrayList < > ();
		data=contestRepo.findAll();
		ContestList = Codechef.Data();
		
		ContestList.forEach((i) ->{
				Contest n = new Contest();
				f=0;
				data.forEach((d)->{
				if(d.getName().equals(i.getName()))
				{
					System.out.println("equal");
					f=1;
					
				}});
				if(f==0)
				{
					n=i;
					contestRepo.save(n);
				}			
				
			    
		}
		);
		
		return "Saved";
	}
		
	
	

}
