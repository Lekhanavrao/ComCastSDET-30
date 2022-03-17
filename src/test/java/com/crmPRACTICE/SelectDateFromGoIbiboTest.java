package com.crmPRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SelectDateFromGoIbiboTest {
	
	@Test
	public void selectDateFromGoIbiboTest()
	{
		  String date = "6";
		  String monthAndYear="April 2022";
		  WebDriver driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  driver.get("https://www.goibibo.com/");
		  
		  driver.findElement(By.xpath("//span[.='Departure']")).click();
		String dateXpath = "//div[.='"+monthAndYear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[.='"+date+"']";
		driver.findElement(By.xpath(dateXpath)).click();
		driver.findElement(By.xpath("//span[.='Done']")).click();
		  
	}


}
