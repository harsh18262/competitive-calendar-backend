package com.CodingCalendar.api.DataParsers.HackerEarth;

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

public class HackerEarth {
	
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
			 
			 JSONArray contestArray=(JSONArray)convert_2_simplejson(response,"response");
			 
			 for(int i=0;i<contestArray.size();i++)
			 {

				 JSONObject contest = (JSONObject)contestArray.get(i);
				 if(contest.get("status").equals("ONGOING")||contest.get("status").equals("UPCOMING")) 
				 {
			 	 String Name = (String) contest.get("title");
				 Date Start_date = Format_Date((String)contest.get("start_utc_tz"));
				 Date End_date = Format_Date((String)contest.get("end_utc_tz"));
				 String Code =(contest.get("url").toString());
				 ContestList.add(new Contest("Hackerearth", Name, Start_date,End_date,Code));
				 }
				
			 }
			 return ContestList;
			
	}
	

	public Object convert_2_simplejson(org.json.JSONObject responsetmp,String key)
		{
				String responsestr=null;
				
				try {
					responsestr = responsetmp.getString(key);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				Object responseobj=(Object) JSONValue.parse(responsestr);
				
				return responseobj;
		}

	public Date Format_Date(String strdate) 
	  {
		  	SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss+00:00");  
		    Date date;
		    date=null;
			try {
				date = formatter1.parse(strdate);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return date;
			}
	  }

}
