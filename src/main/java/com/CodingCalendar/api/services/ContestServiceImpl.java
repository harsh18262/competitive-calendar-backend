package com.CodingCalendar.api.services;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.CodingCalendar.api.DataParsers.DataParsersutil;
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
	
	DataParsersutil datautils = new DataParsersutil();
	
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
	int updates=0;
	int created=0;
	@Override
	public String updatetables(Iterable < Contest > ContestList) 
	{
		Iterable < Contest > data_from_db;
	
		data_from_db=contestRepo.findAll();
		
		
		ContestList.forEach((latest_contest) ->{
				Contest temp = new Contest();
				f=0;
				Iterator<Contest> db_data_itr = data_from_db.iterator();
				while(db_data_itr.hasNext())
				{	Contest data = db_data_itr.next();
				
					if(data.getName().equals(latest_contest.getName()))
					{
						if(data.compare(latest_contest)==false)
						{
							update_entry(latest_contest,data);
							updates++;
							
						}
						f=1;
						break;
						
						
					}
					
					else if (data.equals(latest_contest))
					{
						f=1;
						break;
						
					}
				}

				
				if(f==0)
				{
					temp=latest_contest;
					contestRepo.save(temp);
					created++;
				}			
		});
		LocalDateTime now=LocalDateTime.now();
				
		Last_updated time = timerepo.findById(325).get(); 
		time.setUpdate_timestamp(now); 
		timerepo.save(time);
		update_phases();
		
		String message="Database Updated:\nEntries Updated: "+String.valueOf(updates)+"\nEntries Created: "+String.valueOf(created)+"\nPhases Updated: "+String.valueOf(phase_updates);
		created=0;
		updates=0;
		phase_updates=0;
		return message;
	}

	private void update_entry(Contest new_data,Contest existing)
	{
		Contest temp=contestRepo.findById(existing.getId()).get();
		temp.setStart_time(new_data.getStart_time());
		temp.setEnd_time(new_data.getEnd_time());
		temp.setUrl(new_data.getUrl());
		temp.setPhase(new_data.getPhase());
		contestRepo.save(temp);	
	}
	int phase_updates=0;
	private void update_phases()
	{
		Iterable<Contest> data_from_db=contestRepo.findAll();
		
		
		data_from_db.forEach((contest)->
		{
			var temp_Phase=contest.getPhase();
			if(temp_Phase.equals("Upcoming") || temp_Phase.equals("Running"))
			{
				String Phase=datautils.check_Phase(contest.getStart_time(), contest.getEnd_time());
				
				if(contest.getPhase().equals(Phase)==false )
				{
					Contest temp=contestRepo.findById(contest.getId()).get();
					temp.setPhase(Phase);
					contestRepo.save(temp);	
					phase_updates++;
				}
				
				
			}
		}
				);
	
		
	}


}
