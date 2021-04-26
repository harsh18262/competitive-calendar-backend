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
		options.addArguments("--headless");
		//Initiating your chromedriver
		WebDriver driver=new ChromeDriver(options);

		//Applied wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//maximize window
		driver.manage().window().maximize();
	
		return driver;
	}
	
	
		public String Data() {
			WebDriver driver=CreateDriver();
			
			
			WebElement contests= driver.findElement(By.id("present-contests-data"));
			List<WebElement> contest = contests.findElements(By.tagName("td"));
			for(int i=0;i<24;i++) {
			System.out.println("Data of "+i+"nd row is: \n" + contest.get(i).getText());
			}
		    
			//closing the browser
			driver.close();
			
			
			
			return "test";
			
		}

}
