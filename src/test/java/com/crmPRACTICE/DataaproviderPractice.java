package com.crmPRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataaproviderPractice {
	
	@Test(dataProvider="getData")
	public void sampleDataProvide(String Name, String Model, int quty)
	{
		System.out.println(Name+"----"+Model+"----"+quty);
	}

	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj= new Object[4][3];
		
		obj[0][0]="MI";
		obj[0][1]="13 Pro Max";
		obj[0][2]=25;
		
		obj[1][0]="iphone";
		obj[1][1]="11 Max";
		obj[1][2]=20;
		
		obj[2][0]="vivo";
		obj[2][1]="17 Max";
		obj[2][2]=15;
		
		obj[3][0]="Nokia";
		obj[3][1]="1100";
		obj[3][2]=30;
		
		return obj;

	}

}
