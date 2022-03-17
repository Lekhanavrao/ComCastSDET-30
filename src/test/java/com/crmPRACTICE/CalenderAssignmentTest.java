package com.crmPRACTICE;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CalenderAssignmentTest {

	@Test
	public void selectDateFromGoIbiboTest()
	{
		//String date = "6";
		//String monthAndYear="April 2022";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.goibibo.com/");

		Date d = new Date();
		String d1 = d.toString();
		String[] date = d1.split(" ");
		
		String mon = date[1];
		String day = date[2];
		String year = date[5];
		
		//String DateFormat = day+"-"+mon+"-"+year;
		

		driver.findElement(By.xpath("//span[.='Departure']")).click();
		String dateXpath = "//div[.='"+mon+" "+year+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[.='"+day+"']";
		driver.findElement(By.xpath(dateXpath)).click();
		driver.findElement(By.xpath("//span[.='Done']")).click();





	}

}
