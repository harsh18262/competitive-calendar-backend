package com.CodingCalendar.api.CodeForces;

import java.math.BigInteger;
import java.security.MessageDigest;

import java.time.LocalTime;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.CodingCalendar.api.entities.Contest;

public class CodeForcesDataScrapper {
	
	public String Data()
	{
		return generateapiurl();


	
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
			String key="ada874236cc81e6f468082ddff345fa93b8781f0";
			String secret="ed8af2846b7973a4b9b5adfa242fa3c6e2dc1352";
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
		    
		

}
