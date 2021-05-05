package com.CodingCalendar.api.services;



import java.util.List;

import com.CodingCalendar.api.entities.Contest;





public interface ContestService {
	
	public Iterable<Contest> getallContests();
	public List<Contest> getCodechef();
	public List<Contest> getCodeforces();
	public List<Contest> getHackerearth();
	public String updatetables(Iterable < Contest > ContestList);
	

}
