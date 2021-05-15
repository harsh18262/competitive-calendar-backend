package com.CodingCalendar.api.DataParsers.CodeChef;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.CodingCalendar.api.entities.Contest;
import com.goebl.david.Webb;

public class CodeChef {


  

  public List<Contest> data()
  {
	  Webb webb =Webb.create();
	  String token= "Bearer "+ token_gen();
	  
		org.json.JSONObject response = webb                                                       
		          .get("https://api.codechef.com/contests")
		          .header("Authorization" ,  token)
		          .header("Content-Type", "application/json")
		          .param("status","present")
		          .ensureSuccess()
		          .asJsonObject()
		          .getBody();
		
		JSONObject resultp=convert_2_simplejson(response,"result");
		
		
		 response = webb                                                       
		          .get("https://api.codechef.com/contests")
		          .header("Authorization" ,  token)
		          .header("Content-Type", "application/json")
		          .param("status","future")
		          .ensureSuccess()
		          .asJsonObject()
		          .getBody();
		
		 JSONObject resultf=convert_2_simplejson(response,"result");
		 
		 
		 List<Contest>PresentContest=getContests(resultp);
		 List<Contest>FutureContest=getContests(resultf);
		 List<Contest> ContestList = Stream.of(PresentContest, FutureContest)
                 .flatMap(Collection::stream)
                 .collect(Collectors.toList());
		
		return ContestList;
		
		
	  
  }
  
  
  public  List<Contest> getContests(JSONObject Response)
  {
	  
/*	  "result": {
	    "data": {
	      "code": 9001,
	      "message": "Contests Successfully fetched",
	      "content": {
	        "currentTime": 1620035559,
	        "contestList": [
	          {
	            "code": "AIMICPC",
	            "endDate": "2023-06-17 00:00:00",
	            "name": "AIM ICPC - Weekly Training Series",
	            "startDate": "2020-12-29 00:00:00"
	          }
	        ]
	      }
	    }
	  },
	  "status": "OK"
	}*/
	 	
	  

		JSONObject data =(JSONObject) Response.get("data");
		JSONObject content= (JSONObject)data.get("content");
		JSONArray contestArray =(JSONArray) content.get("contestList");
		
		List<Contest> ContestList=new ArrayList<>();
		 for(int i=0;i<contestArray.size();i++)
		 {

			 JSONObject contest = (JSONObject)contestArray.get(i);
			 String Name = (String) contest.get("name");
			 Date Start_date = Format_Date((String)contest.get("startDate"));
			 Date End_date = Format_Date((String)contest.get("endDate"));
			 String Code = "https://codechef.com/"+(String)contest.get("code");
			 ContestList.add(new Contest("CodeChef", Name, Start_date,End_date,Code));
			
		 }
		
		
		
		return ContestList;
	  
  }
  
  
  public String token_gen() 
  {
	//example response  
	//{"result":
	//	  {"data":
	//		  {"access_token":" ",
	//		    "scope":"public",
	//				"token_type":"Bearer",
	//				"expires_in":3600}},
	//	"status":"OK"}
	  
	  		Webb webb = Webb.create();
			org.json.JSONObject response = webb                                                       
	          .post("https://api.codechef.com/oauth/token")
	          .param("grant_type", "client_credentials")
	          .param("scope", "public")
	          .param("client_id", System.getenv("CODECHEF_ID"))
	          .param("client_secret", System.getenv("CODECHEF_SECRET"))
	          .ensureSuccess()
	          .asJsonObject()
	          .getBody();
		
			JSONObject result=convert_2_simplejson(response,"result");
	
			
			JSONObject data = (JSONObject) result.get("data");
			String token = (String) data.get("access_token");
	
		return token;
	
  } 
	  
		
  public JSONObject convert_2_simplejson(org.json.JSONObject responsetmp,String key)
		{
			String responsestr=null;
			
			try {
				responsestr = responsetmp.getString(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
			JSONObject responseobj=(JSONObject) JSONValue.parse(responsestr);
			
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
	  
  }




