package com.CodingCalendar.api.DataParsers.HackerEarth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.CodingCalendar.api.DataParsers.DataParsersutil;
import com.CodingCalendar.api.entities.Contest;
import com.goebl.david.Webb;

public class HackerEarth {
	
	DataParsersutil datautils = new DataParsersutil();
	
	public List<Contest> data()
	{
		 Webb webb =Webb.create();
		  
		String url= "https://www.hackerearth.com/chrome-extension/events/"; 
			org.json.JSONObject response = webb                                                       
			          .get(url)
			          .ensureSuccess()
			          .asJsonObject()
			          .getBody();
			
			 List < Contest > ContestList;
			 ContestList = new ArrayList<>();
			 
			 JSONArray contestArray=(JSONArray)datautils.convert_2_simplejson(response,"response");
			 String date_format="yyyy-MM-dd HH:mm:ss+00:00";
			 
			 for(int i=0;i<contestArray.size();i++)
			 {

				 JSONObject contest = (JSONObject)contestArray.get(i);
				 if(contest.get("status").equals("ONGOING")||contest.get("status").equals("UPCOMING")) 
				 {
			 	 String Name = (String) contest.get("title");
				 Date Start_date = datautils.Format_Date((String)contest.get("start_utc_tz"),date_format);
				 Date End_date = datautils.Format_Date((String)contest.get("end_utc_tz"),date_format);
				 String Code =(contest.get("url").toString());
				 ContestList.add(new Contest("Hackerearth", Name, Start_date,End_date,Code));
				 }
				
			 }
			 return ContestList;
			
	}
	


}
