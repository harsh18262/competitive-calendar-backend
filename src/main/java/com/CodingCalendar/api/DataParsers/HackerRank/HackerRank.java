package com.CodingCalendar.api.DataParsers.HackerRank;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.CodingCalendar.api.DataParsers.DataParsersutil;
import com.CodingCalendar.api.entities.Contest;
import com.goebl.david.Webb;

public class HackerRank {
	DataParsersutil datautils = new DataParsersutil();
	
	public List<Contest> data(){ 
	Webb webb =Webb.create();
	  
	String url= "https://www.hackerrank.com/rest/contests/upcoming?offset=0&limit=10&contest_slug=active"; 
		org.json.JSONObject response = webb                                                       
		          .get(url)
		          .header("user-agent" , "PostmanRuntime/7.28.0")
		          .ensureSuccess()
		          .asJsonObject()
		          .getBody();
		
		
		 List < Contest > ContestList;
		 ContestList = new ArrayList<>();
		 
		 JSONArray contestArray=(JSONArray)datautils.convert_2_simplejson(response,"models");

	
		 for(int i=0;i<contestArray.size();i++)
		 {
		

			 JSONObject contest = (JSONObject)contestArray.get(i);
			
			 if(contest.get("ended").equals(false)) 
			 {
		 	 String Name = (String) contest.get("name");
			 Date Start_date = datautils.epoch2date((Long)contest.get("epoch_starttime"));
			 Date End_date = datautils.epoch2date((Long)contest.get("epoch_endtime"));
			 String Url ="https://www.hackerrank.com/contests/"+(contest.get("slug").toString());
			 ContestList.add(new Contest("Hackerrank", Name, Start_date,End_date,Url));
			 }
			 
		 }
		 return ContestList;
}



			

}
