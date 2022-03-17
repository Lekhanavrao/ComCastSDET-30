package com.crmPRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class HandlingCalenderTest {
	
	@Test
	public void handlingCalenderTest()
	{
		  String date = "9";
		  String monthAndYear="November 2022";
		  WebDriver driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  driver.get("https://www.makemytrip.com/");
		  
		  Actions act = new Actions(driver);
		  act.moveByOffset(10, 10).click().perform();
		  
		  driver.findElement(By.xpath("//span[.='DEPARTURE']")).click();
		  String arrowXpath = "//span[@aria-label='Next Month']";
		  String dateXpath = "//div[.='"+monthAndYear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[.='"+date+"']";
		 // driver.findElement(By.xpath(dateXpath)).click();
		  
		  for(;;) {
			  try{
				  driver.findElement(By.xpath(dateXpath)).click();
				  break;
			  }
			  catch(Exception e){
				  driver.findElement(By.xpath(arrowXpath)).click();

			  }
		  }
		  
	}

}
