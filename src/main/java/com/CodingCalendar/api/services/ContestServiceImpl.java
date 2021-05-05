package com.CodingCalendar.api.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodingCalendar.api.DataScrappers.CodeChef.CodeChef;
import com.CodingCalendar.api.DataScrappers.CodeForces.CodeForces;
import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.entities.ContestRepository;

@Service
public class ContestServiceImpl implements ContestService {

	
	@Autowired
	private ContestRepository contestRepo;
	
	@Override
	public Iterable<Contest> getContests() {
		CodeChef Data=new CodeChef();
		
		List<Contest> list=Data.Data_api();
		Iterable<Contest> iterablelist=list;
	
		return iterablelist;
	}

	@Override
	public List<Contest> getCodechef() {
		CodeChef Codechef= new CodeChef();
		
		return Codechef.Data_api();
	}

	@Override
	public List<Contest> getCodeforces() {
		CodeForces CodeForces =new CodeForces();
		
		return CodeForces.Data_new();
		
	}
	
	int f;
	@Override
	public String updatetables(Iterable < Contest > ContestList) {
		Iterable < Contest > data;
	
		data=contestRepo.findAll();
	
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
		});
		return "Updated";
	}

}
