package com.CodingCalendar.api.CodeChef;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.CodingCalendar.api.WebDriverCreator;
import com.CodingCalendar.api.entities.Contest;

public class CodeChefDataScrapper {

  public List < Contest > Data() 
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

}

