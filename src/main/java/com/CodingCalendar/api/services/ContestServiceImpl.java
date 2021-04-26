package com.CodingCalendar.api.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.CodingCalendar.api.CodeChef.CodeChefDataScrapper;
import com.CodingCalendar.api.entities.Contest;

@Service
public class ContestServiceImpl implements ContestService {

	
	@Override
	public List<Contest> getContests() {
		CodeChefDataScrapper Data=new CodeChefDataScrapper();
		
		List<Contest> list=Data.Data();
	
		return list;
	}

}
