package com.CodingCalendar.api.CodeChef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.CodingCalendar.api.WebDriverCreator;
import com.CodingCalendar.api.entities.Contest;
import com.goebl.david.Webb;

public class CodeChefDataScrapper {

  public List < Contest > Data_old() 
  {
    
	  WebDriverCreator drivercreator = new WebDriverCreator();
    WebDriver driver = drivercreator.CreateDriver();

    String url = "https://codechef.com/contests";
    driver.get(url);

    WebElement contests = driver.findElement(By.id("present-contests-data"));
    List < WebElement > contest = contests.findElements(By.tagName("td"));
    List < Contest > ContestList;
    ContestList = new ArrayList < > ();
    for (int i = 0; i < contest.size(); i += 4) {
    	 String Name = contest.get(i + 1).getText();
		 Date Start_date =Format_Date(contest.get(i + 2).getText());
		 Date End_date = Format_Date(contest.get(i + 3).getText());;
		 ContestList.add(new Contest("CodeChef", Name ,Start_date , End_date));

    }

    driver.close();

    return ContestList;

  }

  public Date Format_Date(String strdate) 
  {
	  	SimpleDateFormat formatter1=new SimpleDateFormat("dd MMM yyy\nHH:mm:ss");  
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
  

  public List<Contest> Data_api()
  {
	  Webb webb =Webb.create();
	  String token= "Bearer "+ token_gen();
	  System.out.println(token);
	  
		org.json.JSONObject response = webb                                                       
		          .get("https://api.codechef.com/contests")
		          .header("Authorization" ,  token)
		          .header("Content-Type", "application/json")
		          .param("status","present")
		          .ensureSuccess()
		          .asJsonObject()
		          .getBody();
		
		JSONObject resultp=convert_2_simplejson(response);
		
		
		 response = webb                                                       
		          .get("https://api.codechef.com/contests")
		          .header("Authorization" ,  token)
		          .header("Content-Type", "application/json")
		          .param("status","future")
		          .ensureSuccess()
		          .asJsonObject()
		          .getBody();
		
		 JSONObject resultf=convert_2_simplejson(response);
		 
		 
		 List<Contest>PresentContest=GetContests(resultp);
		 List<Contest>FutureContest=GetContests(resultf);
		 List<Contest> ContestList = Stream.of(PresentContest, FutureContest)
                 .flatMap(Collection::stream)
                 .collect(Collectors.toList());
		
		return ContestList;
		
		
	  
  }
  
  
  public  List<Contest> GetContests(JSONObject Response)
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
			 Date Start_date = format_date((String)contest.get("startDate"));
			 Date End_date = format_date((String)contest.get("endDate"));
			 ContestList.add(new Contest("CodeChef", Name, Start_date,End_date));
			
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
		
			JSONObject result=convert_2_simplejson(response);
	
			
			JSONObject data = (JSONObject) result.get("data");
			String token = (String) data.get("access_token");
	
		return token;
	
  } 
	  
		
  public JSONObject convert_2_simplejson(org.json.JSONObject responsetmp)
		{
			String responsestr=null;
			
			try {
				responsestr = responsetmp.getString("result");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
			JSONObject responseobj=(JSONObject) JSONValue.parse(responsestr);
			
			return responseobj;
		}
		
  public Date format_date(String datestr)
		{
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
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




