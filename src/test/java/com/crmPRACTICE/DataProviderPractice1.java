package com.crmPRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice1 {

	@Test(dataProvider="getData")
	public void SampleDataProvider(String Name,int price)
	{

		System.out.println((Name+"---"+price));
		}

	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj= new Object[6][2];
		
		obj[0][0]="Tomoto";
		obj[0][1]=25;
		
		obj[1][0]="Chilly";
		obj[1][1]=15;
		
		obj[2][0]="beans";
		obj[2][1]=10;
		
		obj[3][0]="branzil";
		obj[3][1]=40;
		
		obj[4][0]="Nuggekayi";
		obj[4][1]=100;
		
		obj[5][0]="apple";
		obj[5][1]=10;
		
		return obj;
	}
}
