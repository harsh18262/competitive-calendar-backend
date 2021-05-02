package com.CodingCalendar.api.services;



import java.util.List;

import com.CodingCalendar.api.entities.Contest;





public interface ContestService {
	
	public Iterable<Contest> getContests();
	public List<Contest> getCodechef();
	public List<Contest> getCodeforces();
	public String updatetables(Iterable < Contest > ContestList);
	

}
