package com.CodingCalendar.api.DataParsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.simple.JSONValue;

public class DataParsersutil {
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

	public Date Format_Date(String strdate,String format) 
	{
	  		SimpleDateFormat formatter1=new SimpleDateFormat(format);  
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

	public Date epoch2date(Long unix_seconds)
	{
 	
		Date date =  new Date(unix_seconds* 1000 );	
		return date;
 
	}


}
