package com.CodingCalendar.api.CodeChef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.CodingCalendar.api.entities.Contest;

public class DataScrapper {
	
	public WebDriver CreateDriver()
 {
		System.setProperty("webdriver.chrome.driver", "./chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=1580,1280");
		
		//Initiating your chromedriver
		WebDriver driver=new ChromeDriver(options);


		//Applied wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//maximize window
		driver.manage().window().maximize();
	
		return driver;
	}
	
	
		public List<Contest> Data() {
			WebDriver driver=CreateDriver();
			
			String url="https://codechef.com/contests";
			driver.get(url);
			
			WebElement contests= driver.findElement(By.id("present-contests-data"));
			List<WebElement> contest = contests.findElements(By.tagName("td"));
			List<Contest> ContestList;
			for(int i=0;i<24;i+=4) {
				ContestList.add(new Contest("CodeChef",contest.get(i+1).getText(),contest.get(i+2).getText(),contest.get(i+3).getText()))
			System.out.println("Data of "+ i+"nd row is: \n" + contest.get(i).getText());
			}
		    
			//closing the browser
			driver.close();
			
			
			
			return "test";
			
		}

}
