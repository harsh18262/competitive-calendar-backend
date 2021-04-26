package com.CodingCalendar.api.CodeChef;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.CodingCalendar.api.WebDriverCreator;
import com.CodingCalendar.api.entities.Contest;

public class CodeChefDataScrapper {

  public List < Contest > Data() {
    WebDriverCreator drivercreator = new WebDriverCreator();
    WebDriver driver = drivercreator.CreateDriver();

    String url = "https://codechef.com/contests";
    driver.get(url);

    WebElement contests = driver.findElement(By.id("present-contests-data"));
    List < WebElement > contest = contests.findElements(By.tagName("td"));
    List < Contest > ContestList;
    ContestList = new ArrayList < > ();
    for (int i = 0; i < contest.size(); i += 4) {
      ContestList.add(new Contest("CodeChef", contest.get(i + 1).getText(), contest.get(i + 2).getText(), contest.get(i + 3).getText()));

    }

    driver.close();

    return ContestList;

  }

}