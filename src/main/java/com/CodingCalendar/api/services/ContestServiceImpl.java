package com.CodingCalendar.api.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.CodingCalendar.api.DataParsers.CodeChef.CodeChef;
import com.CodingCalendar.api.DataParsers.CodeForces.CodeForces;
import com.CodingCalendar.api.DataParsers.HackerEarth.HackerEarth;
import com.CodingCalendar.api.DataParsers.HackerRank.HackerRank;
import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.entities.ContestRepository;
import com.CodingCalendar.api.entities.Last_updated;
import com.CodingCalendar.api.entities.Last_updated_Repository;

@Service
public class ContestServiceImpl implements ContestService {

	
	@Autowired
	private ContestRepository contestRepo;
	
	@Autowired
	private Last_updated_Repository timerepo;
	
	@Override
	public List<Contest> getallContests() {
		
		List<Contest> ContestList = new ArrayList<>();
		List<Contest> data=new ArrayList<>();
		
		data=getCodechef();
		ContestList.addAll(data);
		
		data=getCodeforces();
		ContestList.addAll(data);
		
		data=getHackerearth();
		ContestList.addAll(data);
		
		data=getHackerrank();
		ContestList.addAll(data);
		
		
		
		
	
		return ContestList;
	}

	@Override
	public List<Contest> getCodechef() {
		CodeChef Codechef= new CodeChef();
		
		return Codechef.data();
	}

	@Override
	public List<Contest> getCodeforces() {
		CodeForces Codeforces =new CodeForces();
		
		return Codeforces.data();
		
	}
	
	@Override
	public List<Contest> getHackerearth() {
		HackerEarth Hackerearth =new HackerEarth();
		
		return Hackerearth.data();

	}

	@Override
	public List<Contest> getHackerrank() {
		HackerRank Hackerrank =new HackerRank();
		
		return Hackerrank.data();

	}
	
	int f;
	@Override
	public String updatetables(Iterable < Contest > ContestList) 
	{
		Iterable < Contest > data;
	
		data=contestRepo.findAll();
	
		ContestList.forEach((i) ->{
				Contest n = new Contest();
				f=0;
				data.forEach((d)->{
				if(d.getName().equals(i.getName()))
				{
					f=1;
					
				}});
				if(f==0)
				{
					n=i;
					contestRepo.save(n);
				}			
		});
		LocalDateTime now=LocalDateTime.now();
				
		Last_updated time = timerepo.findById(325).get(); 
		time.setUpdate_timestamp(now); 
		timerepo.save(time);
		
		return "Updated";
	}




}
