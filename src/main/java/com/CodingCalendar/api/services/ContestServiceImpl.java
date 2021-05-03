package com.CodingCalendar.api.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodingCalendar.api.CodeChef.CodeChefDataScrapper;
import com.CodingCalendar.api.CodeForces.CodeForcesDataScrapper;
import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.entities.ContestRepository;

@Service
public class ContestServiceImpl implements ContestService {

	
	@Autowired
	private ContestRepository contestRepo;
	
	@Override
	public Iterable<Contest> getContests() {
		CodeChefDataScrapper Data=new CodeChefDataScrapper();
		
		List<Contest> list=Data.Data_api();
		Iterable<Contest> iterablelist=list;
	
		return iterablelist;
	}

	@Override
	public List<Contest> getCodechef() {
		CodeChefDataScrapper Codechef= new CodeChefDataScrapper();
		
		return Codechef.Data_api();
	}

	@Override
	public List<Contest> getCodeforces() {
		CodeForcesDataScrapper CodeForces =new CodeForcesDataScrapper();
		
		List<Contest> url=CodeForces.Data();
		
		
		return url;
		
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
