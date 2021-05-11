package com.CodingCalendar.api.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodingCalendar.api.DataScrappers.CodeChef.CodeChef;
import com.CodingCalendar.api.DataScrappers.CodeForces.CodeForces;
import com.CodingCalendar.api.DataScrappers.HackerEarth.HackerEarth;
import com.CodingCalendar.api.DataScrappers.HackerRank.HackerRank;
import com.CodingCalendar.api.entities.Contest;
import com.CodingCalendar.api.entities.ContestRepository;

@Service
public class ContestServiceImpl implements ContestService {

	
	@Autowired
	private ContestRepository contestRepo;
	
	@Override
	public List<Contest> getallContests() {
		CodeChef Codechef=new CodeChef();
		CodeForces Codeforces =new CodeForces();
		HackerEarth Hackerearth =new HackerEarth();
		HackerRank Hackerrank =new HackerRank();
		
		List<Contest> ContestList = new ArrayList<>();
		List<Contest> data=new ArrayList<>();
		
		data=Codechef.data();
		ContestList.addAll(data);
		
		data=Codeforces.data();
		ContestList.addAll(data);
		
		data=Hackerearth.data();
		ContestList.addAll(data);
		
		data=Hackerrank.data();
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
	public String updatetables(Iterable < Contest > ContestList) {
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
		return "Updated";
	}




}
