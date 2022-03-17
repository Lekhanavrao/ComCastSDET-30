package com.crmPRACTICE;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;

public class PracticeForGenericUtils {
	
@Test
public void practice() throws Throwable
{
	JavaUtility jLib = new JavaUtility();
	int ran = jLib.getRandomNuumber();
	String dat = jLib.getSystemDateInFormat();
	String date = jLib.getSystemDate();
	System.out.println(ran + date);
	System.out.println(dat);
	
	PropertyFileUtility pLib= new PropertyFileUtility();
	String brows = pLib.readDataFromPropertyFile("browser");
	System.out.println(brows);
	
	ExcelFileUtility elib = new ExcelFileUtility();
	elib.readDataFromExcelSheet("Org", 3, 2);
}

}
