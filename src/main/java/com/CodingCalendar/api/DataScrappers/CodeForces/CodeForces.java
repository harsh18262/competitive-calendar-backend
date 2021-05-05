package com.CodingCalendar.api.DataScrappers.CodeForces;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.CodingCalendar.api.entities.Contest;
import com.goebl.david.Webb;




public class CodeForces {
	
	
	//Old function to get contestlist
	public List < Contest > Data()
	{

		try {
			URL url = new URL(generateapiurl());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
			conn.setRequestMethod("GET");
			conn.connect(); 
			int responsecode = conn.getResponseCode(); 
			if(responsecode != 200)
			//throw new RuntimeException(“HttpResponseCode: “ +responsecode);
			System.out.println("error");
			else
			{
				String inline=new String();
				Scanner sc;
				try {
	
					sc = new Scanner(url.openStream());
					while(sc.hasNext())
					{
						inline+=sc.nextLine();
					}
					sc.close();
					
				} 
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 List < Contest > ContestList;
				 ContestList = new ArrayList<>();
				 
				 JSONObject jobj = (JSONObject)JSONValue.parse(inline);
				 
				 JSONArray jsonarr_1 = (JSONArray) jobj.get("result"); 

				 for(int i=0;i<jsonarr_1.size();i++)
				 {

					 JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
					 if(jsonobj_1.get("phase").equals("BEFORE")) 
					 {
				 	 String Name = (String) jsonobj_1.get("name");
					 Date Start_date = epoch2date((Long)(jsonobj_1.get("startTimeSeconds")));
					 Date End_date = epoch2date( (Long)(jsonobj_1.get("startTimeSeconds")) + (Long)(jsonobj_1.get("durationSeconds")));
					// ContestList.add(new Contest("CodeForces", Name, Start_date,End_date));
					 }
					
				 }
				 return ContestList;

			}
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	public List <Contest> Data_new(){
		
		  Webb webb =Webb.create();
		 		  
			org.json.JSONObject response = webb                                                       
			          .get(generateapiurl())
			          .ensureSuccess()
			          .asJsonObject()
			          .getBody();
			
			 List < Contest > ContestList;
			 ContestList = new ArrayList<>();
			 
			 JSONArray contestArray=(JSONArray)convert_2_simplejson(response);

			 for(int i=0;i<contestArray.size();i++)
			 {

				 JSONObject contest = (JSONObject)contestArray.get(i);
				 if(contest.get("phase").equals("BEFORE")) 
				 {
			 	 String Name = (String) contest.get("name");
				 Date Start_date = epoch2date((Long)(contest.get("startTimeSeconds")));
				 Date End_date = epoch2date( (Long)(contest.get("startTimeSeconds")) + (Long)(contest.get("durationSeconds")));
				 String Code =(contest.get("id").toString());
				 ContestList.add(new Contest("CodeForces", Name, Start_date,End_date,Code));
				 }
				
			 }
			 return ContestList;
			
			
		
	}
	
	public static String getSHA512(String input)
	 {

			String toReturn = null;
			try {
			    MessageDigest digest = MessageDigest.getInstance("SHA-512");
			    digest.reset();
			    digest.update(input.getBytes("utf8"));
			    toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			return toReturn;
	 }
	 

	 public String generateapiurl()
	 {
			String key = System.getenv("CODEFORCES_KEY");
			String secret=System.getenv("CODEFORCES_SECRET");
			
		 	long Time=System.currentTimeMillis()/1000;
			String site="https://codeforces.com/api/";
			String method="contest.list?";
			String parameter="gym=false";
			int min=100000;int max=999999;
			long random_number=(long)(Math.random()*(max-min+1)+min);  
			String signature=random_number+"/"+method+"apiKey="+key+"&"+parameter+"&time="+Time+"#"+secret;
			String sha512_hash=getSHA512(signature);
			String url=site+method+parameter+"&apiKey="+key+"&time="+Time+"&apiSig="+random_number+sha512_hash;		
			return url;
	 }
	 
		
	 public Date epoch2date(Long unix_seconds) {
		 	
		 	Date date =  new Date(unix_seconds* 1000 );	
		 	return date;
		 
	 }
	 
	 public Object convert_2_simplejson(org.json.JSONObject responsetmp)
		{
				String responsestr=null;
				
				try {
					responsestr = responsetmp.getString("result");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				Object responseobj=(Object) JSONValue.parse(responsestr);
				
				return responseobj;
		}
}
