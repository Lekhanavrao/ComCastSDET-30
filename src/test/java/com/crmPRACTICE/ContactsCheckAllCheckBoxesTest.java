package com.crmPRACTICE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.HomePage;

public class ContactsCheckAllCheckBoxesTest extends BaseClass {
	
	@Test
	public void contactsCheckAllCheckBoxesTest()
	{
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();	
		
		List<WebElement> elements = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td/input[@name='selected_id']"));
	ArrayList<WebElement> arr = (ArrayList<WebElement>)	elements;
		
		for(WebElement ele:arr)
		{
			ele.click();
		}
	}

}
