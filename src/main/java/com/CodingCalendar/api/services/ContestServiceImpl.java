package com.CodingCalendar.api.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.CodingCalendar.api.entities.Contest;

@Service
public class ContestServiceImpl implements ContestService {

	List<Contest> list;
	public ContestServiceImpl() {
		Date date = new Date();
		list = new ArrayList<>();
		list.add(new Contest("CodeChef","ContestName",date,date));
		list.add(new Contest("CodeChef","ContestName2",date,date));
	}
	@Override
	public List<Contest> getContests() {
	
		return list;
	}

}
