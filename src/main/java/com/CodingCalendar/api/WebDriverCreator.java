package com.CodingCalendar.api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {
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
			
			WebDriver driver=new ChromeDriver(options);

			//Applied wait time
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//maximize window
			driver.manage().window().maximize();
		
			return driver;
		}
		

}
