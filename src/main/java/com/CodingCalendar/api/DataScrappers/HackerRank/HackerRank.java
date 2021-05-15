package com.CodingCalendar.api.DataScrappers.HackerRank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.CodingCalendar.api.entities.Contest;
import com.goebl.david.Webb;

public class HackerRank {
	
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
		 
		 JSONArray contestArray=convert_2_simplejson(response,"models");
	
		 for(int i=0;i<contestArray.size();i++)
		 {
		

			 JSONObject contest = (JSONObject)contestArray.get(i);
			
			 if(contest.get("ended").equals(false)) 
			 {
		 	 String Name = (String) contest.get("name");
			 Date Start_date = epoch2date((Long)contest.get("epoch_starttime"));
			 Date End_date = epoch2date((Long)contest.get("epoch_endtime"));
			 String Url ="https://www.hackerrank.com/contests/"+(contest.get("slug").toString());
			 ContestList.add(new Contest("Hackerrank", Name, Start_date,End_date,Url));
			 }
			 
		 }
		 return ContestList;
}

		 public JSONArray convert_2_simplejson(org.json.JSONObject responsetmp,String key)
			{
				String responsestr=null;
				
				try {
					responsestr = responsetmp.getString(key);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				JSONArray responseobj=(JSONArray) JSONValue.parse(responsestr);
				
				return responseobj;
			}
		 
		 public Date Format_Date(String datestr)
			{
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2021-05-24 00:00:00
				//formatter.setTimeZone(TimeZone.getTimeZone("IST"));
			 	Date date;
				try {
					date = formatter.parse(datestr);
					return date;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}	
				
			}
		 public Date epoch2date(Long unix_seconds) {
			 	
			 	Date date =  new Date(unix_seconds* 1000 );	
			 	return date;
			 
		 }
		 
			

}
